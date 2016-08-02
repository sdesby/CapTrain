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
			latitude = str(result[0]['geometry']['lat'])
			print latitude
			longitude = str(result[0]['geometry']['lng'])
			print longitude
			url = "http://localhost:8080/restserver/station/nearest-station?latitude=" + latitude + "&longitude=" + longitude
			print url
			request = Request(url)

			try:
				response = urlopen(request)
				stationRoot = ET.fromstring(response.read())
				m_id = stationRoot.find("id").text
				name = stationRoot.find("name").text
				howbig = stationRoot.find("howbig").text
				latitude = stationRoot.find("coordinates/latitude").text
				longitude = stationRoot.find("coordinates/longitude").text
				postal_code = stationRoot.find("postalCode").text
				city = stationRoot.find("city").text
				department = stationRoot.find("department").text
				region =stationRoot.find("region").text
				station = TrainStation()
				station.initialize(m_id, name, howbig, latitude, longitude, postal_code, city, department, region)
				return station

			except URLError, e:
				print "Erreur lors de la communication avec le service distant :" , e
				return -1
