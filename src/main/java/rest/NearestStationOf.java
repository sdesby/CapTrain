package rest;

import model.Coordinates;
import model.TrainStation;
import service.TrainStationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("station")
public class NearestStationOf {

   @GET
   @Path("/nearest-station")
   public Response findNearestStationOf(@QueryParam("latitude") double latitude,
                                        @QueryParam("longitude") double longitude) {

      Coordinates userCoordinates = new Coordinates(latitude, longitude);
      TrainStationService trainStationService = new TrainStationService();

      TrainStation nearestTrainStation = trainStationService.findNearestStationOf(userCoordinates);

      return Response
            .status(200)
            .entity("Nom de la gare la plus proche : " + nearestTrainStation.getName() + "(" + nearestTrainStation.getId() + ")\n" +
                  "Coordonnées GPS de la Gare : Latitude = " + nearestTrainStation.getCoordinates().getLatitude()
                  + "  Longitude = " + nearestTrainStation.getCoordinates().getLongitude())
            .build();
   }
}
