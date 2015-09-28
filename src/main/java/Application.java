import java.net.UnknownHostException;
import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.mongodb.MongoClient;

public class Application {

   public static final String DB_NAME = "trainStation";
   public static final String TRAIN_STATION_COLLECTION = "train_stations";
   public static final String MONGO_HOST = "localhost";
   public static final int MONGO_PORT = 27017;

   public static void main(String[] args)
   {
      try {
         MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
         MongoOperations mongoOps = new MongoTemplate(mongo, DB_NAME);

         List<TrainStation> trainStation = mongoOps.find(new Query(Criteria.where("name").is("Vire")), TrainStation.class, TRAIN_STATION_COLLECTION);

         for (TrainStation station : trainStation) {
            System.out.println(station.toString());
         }
      }
      catch (UnknownHostException e){
         e.printStackTrace();
      }
   }
}
