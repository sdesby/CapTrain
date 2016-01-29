#!/usr/bin/env python

from urllib2 import Request, urlopen, URLError

print ("Bonjour, ici vous pouvez rechercher la gare la plus proche d'une localisation GPS")
latitude = raw_input("Entrer la latitude : ")
longitude = raw_input("Entrer la longitude : ")

request = Request("http://localhost:8080/restserver/station/nearest-station?latitude=" + latitude + "&longitude=" + longitude)

try:
	response = urlopen(request)
	nearestStation = response.read()
	print nearestStation
except URLError, e:
	print "Got an error :" , e
