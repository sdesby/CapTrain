import engine.DistanceCalculator;
import model.Coordinates;
import model.TrainStation;
import service.TrainStationService;

import java.util.List;

public class Application {

   public static final String TRAIN_STATION_COLLECTION = "train_stations";
   private TrainStationService trainStationService;

   public static void main(String[] args) {
      TrainStationService trainStationService = new TrainStationService();

      List<TrainStation> trainStation = trainStationService.findStationByName("Vire");
      List<TrainStation> trainStation2 = trainStationService.findStationByName("Dole Ville");

      Coordinates trainStationFrom = new Coordinates(
              Double.parseDouble(trainStation.get(0).getLatitude()),
              Double.parseDouble(trainStation.get(0).getLongitude()));

      Coordinates trainStationStationT = new Coordinates(
              Double.parseDouble(trainStation2.get(0).getLatitude()),
              Double.parseDouble(trainStation2.get(0).getLongitude()));

      DistanceCalculator distanceCalculator = new DistanceCalculator(trainStationFrom, trainStationStationT);

      double distance = distanceCalculator.getDistance();

      System.out.println("Gare de " + trainStation.get(0).getName()
            + " qui a pour latitude : " + trainStation.get(0).getLatitude()
            + " et pour longitude : " + trainStation.get(0).getLongitude());
      System.out.println("Gare de " + trainStation2.get(0).getName()
            + " qui a pour latitude : " + trainStation2.get(0).getLatitude()
            + " et pour longitude : " + trainStation2.get(0).getLongitude());
      System.out.println("Distance à vol d'oiseau entre ces deux gares : " + distance + "km");

      System.out.println("#####################################");

      Coordinates userCoordinates = new Coordinates(48.619948, 3.754868);
      TrainStation nearestTrainStation = trainStationService.findNearestStationOf(userCoordinates);

      System.out.println("Nom de la gare la plus proche : " + nearestTrainStation.getId() + " " + nearestTrainStation.getName());
   }
}
