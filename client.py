#!/usr/bin/env python

from urllib2 import Request, urlopen, URLError
from opencage.geocoder import OpenCageGeocode
import json

key = '***REMOVED***'
geocoder = OpenCageGeocode(key)

query = raw_input("Entrez l'adresse recherchee ici : ")
result = geocoder.geocode(query, format='json',language='fr')

latitude = str(result[0]['geometry']['lat'])
longitude = str(result[0]['geometry']['lng'])

#print ("Bonjour, ici vous pouvez rechercher la gare la plus proche d'une localisation GPS")
#latitude = raw_input("Entrer la latitude : ")
#longitude = raw_input("Entrer la longitude : ")
print ""
print ""
print "Nous allons rechercher la Gare la plus proche de l'adresse : " + query
url = "http://localhost:8080/restserver/station/nearest-station?latitude=" + latitude + "&longitude=" + longitude

request = Request(url)

try:
	response = urlopen(request)
	nearestStation = response.read()
	print nearestStation
except URLError, e:
	print "Got an error :" , e
