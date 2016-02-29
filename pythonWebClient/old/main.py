#!/usr/bin/env python

import kivy
kivy.require('1.9.0')

from kivy.app import App
from kivy.uix.label import Label

class MyApp(App):
	
	def build(self):
		return Label(text='Hello Stephanie')
		
if __name__ == '__main__':
	MyApp().run()
