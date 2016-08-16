package rest;

import model.Coordinates;
import model.TrainStation;
import model.TrainStationsList;
import service.TrainStationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

@Path("station")
public class NearestStationOf {

   @GET
   @Path("/nearest-station")
   @Produces("application/xml;charset=UTF-8")
   public Response findNearestStationOf(@QueryParam("latitude") double latitude,
                                        @QueryParam("longitude") double longitude) throws JAXBException {

      Coordinates userCoordinates = new Coordinates(latitude, longitude);
      TrainStationService trainStationService = new TrainStationService();

      TrainStationsList<TrainStation> nearestTrainStations = trainStationService.findNearestStationOf(userCoordinates);

      return Response
            .status(200)
            .entity(nearestTrainStations)
            .build();
   }
}