import model.TrainStation;
import service.TrainStationService;

import java.util.List;

public class Application {

   public static final String TRAIN_STATION_COLLECTION = "train_stations";
   private TrainStationService trainStationService;

   private static double distanceTo2(double userLatitude, double userLongitude, double trainStationLatitude, double trainStationLongitude) {
      //source = http://www.movable-type.co.uk/scripts/latlong.html
      double earthRadius = 6371000.0;

      double userLatToRad = Math.toRadians(userLatitude);
      double trainStationLatToRad = Math.toRadians(trainStationLatitude);

      double deltaLatitude = Math.toRadians(trainStationLatitude - userLatitude);
      double deltaLongitude = Math.toRadians(trainStationLongitude - userLongitude);

      double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)
            + Math.cos(userLatToRad) * Math.cos(trainStationLatToRad)
            * Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);

      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

      double distance = (earthRadius * c) / 1000; //in km

      return distance;
   }

   public static void main(String[] args) {
      TrainStationService trainStationService = new TrainStationService();

      List<TrainStation> trainStation = trainStationService.findStationByName("Vire");
      List<TrainStation> trainStation2 = trainStationService.findStationByName("Dole Ville");

      double distance = distanceTo2(Double.parseDouble(trainStation.get(0).getLatitude()), Double.parseDouble(trainStation.get(0).getLongitude()),
            Double.parseDouble(trainStation2.get(0).getLatitude()), Double.parseDouble(trainStation2.get(0).getLongitude()));

      System.out.println("Gare de " + trainStation.get(0).getName()
            + " qui a pour latitude : " + trainStation.get(0).getLatitude()
            + " et pour longitude : " + trainStation.get(0).getLongitude());
      System.out.println("Gare de " + trainStation2.get(0).getName()
            + " qui a pour latitude : " + trainStation2.get(0).getLatitude()
            + " et pour longitude : " + trainStation2.get(0).getLongitude());
      System.out.println("Distance à vol d'oiseau entre ces deux gares : " + distance + "km");
   }
}
