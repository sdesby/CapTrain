package service;

import dao.OperationsProvider;
import dao.TrainStationPOJO;
import engine.DistanceCalculator;
import model.Coordinates;
import model.TrainStation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TrainStationService {

   private static final String TRAIN_STATION_COLLECTION = "trainStation";
   private final OperationsProvider operationsProvider;

   public TrainStationService() {
      operationsProvider = new OperationsProvider();
   }

   public List<TrainStation> findAll() {
      List<TrainStationPOJO> trainStationPOJOs = operationsProvider.getMongoOps().findAll(TrainStationPOJO.class, TRAIN_STATION_COLLECTION);

      List<TrainStation> trainStations = new ArrayList<TrainStation>();
      trainStationPOJOs.forEach(i -> {
               trainStations.add(new TrainStation(i.getId(), i.getName(), i.getHowbig(), i.getLatitude(), i.getLongitude(), i.getPostalCode(), i.getCity(), i.getDepartment(), i.getRegion()));
            }
      );
      return trainStations;
   }

   public List<TrainStation> findStationByName(String stationName) {
      List<TrainStation> trainStations = new ArrayList<TrainStation>();
      try {
         List<TrainStationPOJO> trainStationPOJOs = operationsProvider.getMongoOps().find(new Query(Criteria.where("name").is(stationName)), TrainStationPOJO.class, TRAIN_STATION_COLLECTION);
         trainStationPOJOs.forEach(i -> {
                  trainStations.add(new TrainStation(i.getId(), i.getName(), i.getHowbig(), i.getLatitude(), i.getLongitude(), i.getPostalCode(), i.getCity(), i.getDepartment(), i.getRegion()));
               }
         );
      } catch (Exception e) {
         System.out.println("Error : " + e.toString());
         return null;
      }
      return trainStations;
   }

   public TrainStation findNearestStationOf(Coordinates userCoordinates) {
      TrainStation trainStation = new TrainStation();
      TrainStation currentStation;

      double shorterDistance = 999999999;
      double currentDistance;

      List<TrainStation> allStations = findAll();
      Iterator<TrainStation> i = allStations.iterator();


      while (i.hasNext()) {
         currentStation = i.next();
         currentDistance = DistanceCalculator.getDistance(userCoordinates, currentStation.getCoordinates());

         if (currentDistance < shorterDistance) {
            shorterDistance = currentDistance;
            trainStation = currentStation;
         }
      }
      System.out.println("Distance de votre position à cette gare : " + shorterDistance);
      return trainStation;
   }
}
