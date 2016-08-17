from urllib2 import Request, urlopen, URLError
from opencage.geocoder import OpenCageGeocode
import xml.etree.ElementTree as ET
import json
from .utils.MyConfigParser import MyConfigParser
from .models import TrainStation

config_parser = MyConfigParser()
BASE_URL = config_parser.ConfigSectionMap("NTS")["baseurl"]

class Geocode:

	def __init__(self):
		self.geocoder = OpenCageGeocode(config_parser.ConfigSectionMap("OpenCageGeocode")["key"])


	def getNearestStation(self, address):
		result = self.geocoder.geocode(address, format='json',language='fr')
		if (result == []):
			return "Aucun resultat pour cette adresse"

		else:
			print "*------ Result : "
			print result
			print "----------------*"
			latitude = str(result[0]['geometry']['lat'])
			print "Latitude : " + latitude
			longitude = str(result[0]['geometry']['lng'])
			print "Longitude : " +longitude
			url = BASE_URL + "?latitude=" + latitude + "&longitude=" + longitude
			print url
			request = Request(url)

			try:
				response = urlopen(request)
				stations_list = []
				station_root = ET.fromstring(response.read())
				for child in station_root:
					m_id = child.find("id").text
					name = child.find("name").text
					howbig = child.find("howbig").text
					latitude = child.find("coordinates/latitude").text
					longitude = child.find("coordinates/longitude").text
					postal_code = child.find("postalCode").text
					city = child.find("city").text
					department = child.find("department").text
					region = child.find("region").text
					station = TrainStation()
					station.initialize(m_id, name, howbig, latitude, longitude, postal_code, city, department, region)
					stations_list.append(station);
				return stations_list

			except URLError, e:
				print "Erreur lors de la communication avec le service distant :" , e
				return -1
