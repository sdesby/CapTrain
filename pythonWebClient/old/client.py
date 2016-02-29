#!/usr/bin/env python

from urllib2 import Request, urlopen, URLError
from opencage.geocoder import OpenCageGeocode
import xml.etree.ElementTree as ET
import json

#--------------------------------------------------#
class TrainStation:
		
	def __init__(self, m_id, name, latitude, longitude):
		self.m_id=m_id
		self.prettyName=name
		self.latitude=latitude
		self.longitude=longitude

	def _get_prettyName(self):
		return self.prettyName

	def _set_prettyName(self, name):
		self.prettyName = name
#--------------------------------------------------#
key = '***REMOVED***'
geocoder = OpenCageGeocode(key)

query = raw_input("Entrez l'adresse recherchee ici : ")
result = geocoder.geocode(query, format='json',language='fr')

latitude = str(result[0]['geometry']['lat'])
longitude = str(result[0]['geometry']['lng'])

print ""
print ""
print "Nous allons rechercher la Gare la plus proche de l'adresse : " + query
url = "http://localhost:8080/restserver/station/nearest-station?latitude=" + latitude + "&longitude=" + longitude

request = Request(url)

try:
	response = urlopen(request)
	stationRoot = ET.fromstring(response.read())
	m_id = stationRoot.find("id").text
	name = stationRoot.find("name").text
	latitude = stationRoot.find("coordinates/latitude").text
	longitude = stationRoot.find("coordinates/longitude").text
	station = TrainStation(m_id, name, latitude, longitude)
	
	print "Nom de la Gare : " + station.prettyName
	print "Coordonnees geographiques : " + station.latitude + ", " + station.longitude
	
except URLError, e:
	print "Got an error :" , e
