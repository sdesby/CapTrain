package restServer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

   @GET
   @Produces(MediaType.TEXT_PLAIN)
   public String sayPlainTextHello() {
      return "Hello Stephanie";
   }

}
