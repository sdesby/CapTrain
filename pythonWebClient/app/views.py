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
		stations_list = geocoder.getNearestStation(address)
		if isinstance(stations_list, str):
			error = stations_list
			return render_template('index.html', title='Nearest station', form=form, address=address, stations=None, error=error)
		else:
			return render_template('index.html', title='Nearest station', form=form, address=address, stations=stations_list, error="")
	return render_template('index.html', title='Nearest station', form=form, address="", stations="", error="")
