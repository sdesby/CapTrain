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
		return render_template('station.html', address=address, station=station._get_prettyName())	
	return render_template('index.html', title='Nearest station', form=form, error="")