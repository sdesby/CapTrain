import java.io.IOException;
import java.net.URI;
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

      //Start REST API Server
      final HttpServer httpServer = startServer();
      System.out.println(String.format("Jersey app started with WADL available at " + "%s/application.wadl\nHit enter to stop it...", BASE_URI));
      try {
         System.in.read();
         httpServer.stop();
      }
      catch(IOException io) {
         System.out.println("IOException");
      }

   }
}
