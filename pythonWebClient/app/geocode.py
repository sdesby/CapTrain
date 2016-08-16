from urllib2 import Request, urlopen, URLError
from opencage.geocoder import OpenCageGeocode
import xml.etree.ElementTree as ET
import json
from .models import TrainStation

class Geocode:

	def __init__(self):
		key = 'Insert token here!'
		self.geocoder = OpenCageGeocode('995f38d3b781b46526e1b3fd7e9a78a7')


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
			url = "http://localhost:8080/restserver/station/nearest-station?latitude=" + latitude + "&longitude=" + longitude
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
