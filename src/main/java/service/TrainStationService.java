package service;

import dao.OperationsProvider;
import engine.DistanceCalculator;
import model.Coordinates;
import model.TrainStation;

import java.util.Iterator;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TrainStationService {

   private static final String TRAIN_STATION_COLLECTION = "train_stations";
   private final OperationsProvider operationsProvider;

   public TrainStationService() {
      operationsProvider = new OperationsProvider();
   }

   public List<TrainStation> findAll(){
      return operationsProvider.getMongoOps().findAll(TrainStation.class, TRAIN_STATION_COLLECTION);
   }

   public List<TrainStation> findStationByName(String stationName){
      return operationsProvider.getMongoOps().find(new Query(Criteria.where("name").is(stationName)), TrainStation.class, TRAIN_STATION_COLLECTION);
   }

   public TrainStation findNearestStationOf(Coordinates userCoordinates) {
      TrainStation trainStation = new TrainStation();
      double shorterDistance = 999999999;
      double currentDistance;

      List<TrainStation> allStations = findAll();
      Iterator<TrainStation> i = allStations.iterator();

      Coordinates stationCoordinates = new Coordinates();
      TrainStation currentStation;
      DistanceCalculator distanceCalculator = new DistanceCalculator();
      distanceCalculator.setCoordinates1(userCoordinates);

      while(i.hasNext()) {
         currentStation = i.next();
         stationCoordinates.setLatitude(Double.parseDouble(currentStation.getLatitude()));
         stationCoordinates.setLongitude(Double.parseDouble(currentStation.getLongitude()));

         distanceCalculator.setCoordinates2(stationCoordinates);
         currentDistance = distanceCalculator.getDistance();

         if(currentDistance < shorterDistance) {
            shorterDistance = currentDistance;
            trainStation = currentStation;
         }
      }
      System.out.println("Distance de votre position à cette gare : " + shorterDistance);
      return trainStation;
   }
}
