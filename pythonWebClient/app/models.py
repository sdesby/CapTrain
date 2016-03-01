class TrainStation:
		
	def initialize(self, m_id, name, latitude, longitude):
		self.m_id=m_id
		self.prettyName=name
		self.latitude=latitude
		self.longitude=longitude

	def _get_prettyName(self):
		return self.prettyName

	def _set_prettyName(self, name):
		self.prettyName = name
		
	def _get_latitude(self):
		return self.latitude
	def _get_longitude(self):
		return self.longitude