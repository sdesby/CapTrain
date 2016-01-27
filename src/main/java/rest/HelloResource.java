package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
public class HelloResource {

   @GET
   @Path("/tome")
   @Produces(MediaType.TEXT_PLAIN)
   public String sayPlainTextHello() {
      return "Hello Stephanie";
   }

   @GET
   @Path("/toeverybody")
   public Response sayHelloToName(@QueryParam("name") String name) {
      if (name.isEmpty())
         return Response
               .status(200)
               .entity("Hello World")
               .build();
      else
         return Response
               .status(200)
               .entity("Hello " + name)
               .build();
   }

}
