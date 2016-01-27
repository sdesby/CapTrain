import engine.DistanceCalculator;
import model.Coordinates;
import model.TrainStation;
import service.TrainStationService;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Application {

   public static final String BASE_URI = "http://localhost:8080/restserver";

   public static HttpServer startServer() {
      final ResourceConfig resourceConfig = new ResourceConfig().packages("rest");
      return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
   }

   public static void main(String[] args) {
      TrainStationService trainStationService = new TrainStationService();

//      findDistanceBetweenStations(trainStationService);
//      findNearestStationFrom(trainStationService);

      //Start REST API Server
      final HttpServer httpServer = startServer();
      System.out.println(String.format("Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
      try {
         System.in.read();
         httpServer.stop();
      }
      catch(IOException io) {
         System.out.println("IOException");
      }

   }

   private static void findNearestStationFrom(TrainStationService trainStationService) {
      //      Coordinates userCoordinates = new Coordinates(48.619948, 3.754868); //Saint Quentin
      Coordinates userCoordinates = new Coordinates(48.866796, 2.364147);
      TrainStation nearestTrainStation = trainStationService.findNearestStationOf(userCoordinates);

      System.out.println("Nom de la gare la plus proche : " + nearestTrainStation.getId() + " " + nearestTrainStation.getName());
   }

   private static void findDistanceBetweenStations(TrainStationService trainStationService) {
      List<TrainStation> trainStation = trainStationService.findStationByName("Vire");
      List<TrainStation> trainStation2 = trainStationService.findStationByName("Dole Ville");

      double distance = DistanceCalculator
            .getDistance(trainStation.get(0).getCoordinates(), trainStation2.get(0).getCoordinates());

      System.out.println("Gare de " + trainStation.get(0).getName()
            + " qui a pour latitude : " + trainStation.get(0).getCoordinates().getLatitude()
            + " et pour longitude : " + trainStation.get(0).getCoordinates().getLongitude());
      System.out.println("Gare de " + trainStation2.get(0).getName()
            + " qui a pour latitude : " + trainStation2.get(0).getCoordinates().getLatitude()
            + " et pour longitude : " + trainStation2.get(0).getCoordinates().getLongitude());
      System.out.println("Distance à vol d'oiseau entre ces deux gares : " + distance + "km");

      System.out.println("#####################################");
   }
}
