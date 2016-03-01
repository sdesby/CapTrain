from urllib2 import Request, urlopen, URLError
from opencage.geocoder import OpenCageGeocode
import xml.etree.ElementTree as ET
import json
from .models import TrainStation

class Geocode:
	
	def __init__(self):
		key = '***REMOVED***'
		self.geocoder = OpenCageGeocode(key)

	
	def getNearestStation(self, address):
		result = self.geocoder.geocode(address, format='json',language='fr')
		latitude = str(result[0]['geometry']['lat'])
		longitude = str(result[0]['geometry']['lng'])
		url = "http://localhost:8080/restserver/station/nearest-station?latitude=" + latitude + "&longitude=" + longitude
		
		request = Request(url)

		try:
			response = urlopen(request)
			stationRoot = ET.fromstring(response.read())
			m_id = stationRoot.find("id").text
			name = stationRoot.find("name").text
			latitude = stationRoot.find("coordinates/latitude").text
			longitude = stationRoot.find("coordinates/longitude").text
			station = TrainStation()
			station.initialize(m_id, name, latitude, longitude)	
			return station
			
		except URLError, e:
			print "Got an error :" , e
			return -1