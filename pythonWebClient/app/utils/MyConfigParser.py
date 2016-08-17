import ConfigParser

class MyConfigParser:

    def __init__(self):
        self.config_parser = ConfigParser.ConfigParser()
        self.config_parser.read("/Users/sdesby/Documents/Perso/NearestTrainStation/config.properties")

    def get_config_parser(self):
        return self.config_parser

    def ConfigSectionMap(self,section):
        dict1 = {}
        options = self.config_parser.options(section)
        for option in options:
            try:
                dict1[option] = self.config_parser.get(section, option)
                if dict1[option] == -1:
                    DebugPrint("skip: %s" % option)
            except:
                print("exception on %s!" % option)
                dict1[option] = None
        return dict1
