from flask import render_template
from app import app

from .form import AddressForm

@app.route('/', methods=['GET', 'POST'])

def index():
        form = AddressForm()
        if form.validate_on_submit():
            print'This the address you entered : %s' % (form.address.data)
	return render_template('index.html', title='Home', form=form)
