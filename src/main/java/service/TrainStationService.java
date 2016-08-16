package service;

import dao.OperationsProvider;
import dao.TrainStationPOJO;
import engine.DistanceCalculator;
import model.Coordinates;
import model.TrainStation;
import model.TrainStationsList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainStationService {

   private static final String TRAIN_STATION_COLLECTION = "trainStation";
   private final OperationsProvider operationsProvider;

   public TrainStationService() {
      operationsProvider = new OperationsProvider();
   }

   public List<TrainStation> findAllStations() {
      List<TrainStationPOJO> trainStationPOJOs = operationsProvider.getMongoOps().findAll(TrainStationPOJO.class, TRAIN_STATION_COLLECTION);

      List<TrainStation> trainStations = new ArrayList<>();
      trainStationPOJOs.forEach(i -> {
               trainStations.add(new TrainStation(i.getId(), i.getName(), i.getHowbig(), i.getLatitude(), i.getLongitude(), i.getPostalCode(), i.getCity(), i.getDepartment(), i.getRegion()));
            }
      );
      return trainStations;
   }

   public TrainStationsList<TrainStation> findNearestStationOf(Coordinates userCoordinates) {

      List<TrainStation> stations = findAllStations();
      Map<Double, TrainStation> distanceAndTrainStation = new HashMap<>();

      stations.forEach(s -> distanceAndTrainStation.put(DistanceCalculator.getDistance(userCoordinates, s.getCoordinates()), s));
      List<Double> sortedDistances = new ArrayList<>(distanceAndTrainStation.keySet());
      Collections.sort(sortedDistances);

      TrainStationsList<TrainStation> trainStationsList = new TrainStationsList<>();

      for (int i = 0; i < 3; i++) {
         trainStationsList.getTrainStationList().add(distanceAndTrainStation.get(sortedDistances.get(i)));
      }

      return trainStationsList;
   }
}
