package service;

import dao.OperationsProvider;
import model.TrainStation;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TrainStationService {

   private static final String TRAIN_STATION_COLLECTION = "train_stations";
   private final OperationsProvider operationsProvider;

   public TrainStationService() {
      operationsProvider = new OperationsProvider();
   }

   public List<TrainStation> findStationByName(String stationName){
      return operationsProvider.getMongoOps().find(new Query(Criteria.where("name").is(stationName)), TrainStation.class, TRAIN_STATION_COLLECTION);
   }
}
