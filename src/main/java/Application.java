import model.TrainStation;
import service.OperationsProvider;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class Application {

   public static final String TRAIN_STATION_COLLECTION = "train_stations";

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
      OperationsProvider operationsProvider = new OperationsProvider();
      List<TrainStation> trainStation = operationsProvider.get().find(new Query(Criteria.where("name").is("Vire")), TrainStation.class, TRAIN_STATION_COLLECTION);
      List<TrainStation> trainStation2 = operationsProvider.get().find(new Query(Criteria.where("name").is("Périgueux")), TrainStation.class, TRAIN_STATION_COLLECTION);

      double distance = distanceTo2(Double.parseDouble(trainStation.get(0).getLatitude()), Double.parseDouble(trainStation.get(0).getLongitude()),
            Double.parseDouble(trainStation2.get(0).getLatitude()), Double.parseDouble(trainStation2.get(0).getLongitude()));

      System.out.println("Gare de " + trainStation.get(0).getName()
            + " qui a pour latitude : " + trainStation.get(0).getLatitude()
            + " et pour longitude : " + trainStation.get(0).getLongitude());
      System.out.println("Gare de " + trainStation2.get(0).getName()
            + " qui a pour latitude : " + trainStation2.get(0).getLatitude()
            + " et pour longitude : " + trainStation2.get(0).getLongitude());
      System.out.println("Distance entre ces deux gares : " + distance + "km");


      List<TrainStation> trainStation3 = operationsProvider.get().find(new Query(Criteria.where("name").is("Bordeaux")), TrainStation.class, TRAIN_STATION_COLLECTION);

      double distance2 = distanceTo2(Double.parseDouble(trainStation3.get(0).getLatitude()), Double.parseDouble(trainStation3.get(0).getLongitude()),
            Double.parseDouble(trainStation2.get(0).getLatitude()), Double.parseDouble(trainStation2.get(0).getLongitude()));

      System.out.println("Gare de " + trainStation3.get(0).getName()
            + " qui a pour latitude : " + trainStation3.get(0).getLatitude()
            + " et pour longitude : " + trainStation.get(0).getLongitude());
      System.out.println("Gare de " + trainStation2.get(0).getName()
            + " qui a pour latitude : " + trainStation2.get(0).getLatitude()
            + " et pour longitude : " + trainStation2.get(0).getLongitude());
      System.out.println("Distance entre ces deux gares : " + distance2 + "km");

   }
}
