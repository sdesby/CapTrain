from flask import render_template
from app import app

from .models import TrainStation
from .form import AddressForm
from .geocode import Geocode

@app.route('/', methods=['GET', 'POST'])

def index():
	form = AddressForm()
	if form.validate_on_submit():
		address=form.address.data
		geocoder = Geocode()
		station = geocoder.getNearestStation(address)
		if isinstance(station, str):
			error = station
			return render_template('index.html', title='Nearest station', form=form, address=address, station=None, error=error)	
		else:
			return render_template('index.html', title='Nearest station', form=form, address=address, station=station, error="")	
	return render_template('index.html', title='Nearest station', form=form, address="", station="", error="")
