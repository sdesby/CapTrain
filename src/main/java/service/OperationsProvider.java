package service;

import java.net.UnknownHostException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;

public class OperationsProvider {

   private static final String DB_NAME = "trainStation";
   private static final String MONGO_HOST = "localhost";
   private static final int MONGO_PORT = 27017;

   private static MongoOperations mongoOps;

   public OperationsProvider()
   {
      MongoClient mongo = null;
      try {
         mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
         mongoOps = new MongoTemplate(mongo, DB_NAME);
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }

   }

   public MongoOperations get() {
      return mongoOps;
   }
}
