package model;

import dao.TrainStationPOJO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
      name = "trainStation"
)
public class TrainStation {
   @XmlElement(
         name = "id",
         required = true
   )
   private final String id;
   @XmlElement(
         name = "name",
         required = true
   )
   private final String name;
   @XmlElement(
         name = "coordinates",
         required = true
   )
   private final Coordinates coordinates;
   @XmlElement(
         name = "id",
         required = false
   )
   private final String slug;
   @XmlElement(
         name = "country",
         required = true
   )
   private final String country;
   @XmlElement(
         name = "info",
         required = false
   )
   private final String info;
   private final boolean isSuggestable;

   public TrainStation() {
      this.id = "";
      this.name = "";
      this.slug = "";
      this.coordinates = new Coordinates(0.0, 0.0);
      this.country = "";
      this.info = "";
      this.isSuggestable = false;
   }

   public TrainStation(TrainStationPOJO trainStationPOJO) {
      this.id = trainStationPOJO.getId();
      this.name = trainStationPOJO.getName();
      this.coordinates = new Coordinates(trainStationPOJO.getLatitude(), trainStationPOJO.getLongitude());
      this.slug = trainStationPOJO.getSlug();
      this.country = trainStationPOJO.getCountry();
      this.info = trainStationPOJO.getInfo();

      if (trainStationPOJO.getIs_suggestable().equals("t")) {
         this.isSuggestable = true;
      } else if (trainStationPOJO.getIs_suggestable().equals("f")) {
         this.isSuggestable = false;
      } else {
         this.isSuggestable = false;
      }
   }

   public TrainStation(String id, String name, String latitude, String longitude, String slug, String country, String info, String isSuggestable) {
      this.id = id;
      this.name = name;
      this.slug = slug;
      this.coordinates = new Coordinates(latitude, longitude);
      this.country = country;
      this.info = info;

      if (isSuggestable.equals("t")) {
         this.isSuggestable = true;
      } else if (isSuggestable.equals("f")) {
         this.isSuggestable = false;
      } else {
         this.isSuggestable = false;
      }
   }

   public String getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public Coordinates getCoordinates() {
      return coordinates;
   }

   public String getSlug() {
      return slug;
   }

   public String getCountry() {
      return country;
   }

   public String getInfo() {
      return info;
   }

   public boolean isSuggestable() {
      return isSuggestable;
   }

   @Override
   public String toString() {
      return String.format(
            "model.TrainStation[id=%s, name='%s', slug='%s', coordinates.latitude='%s', coordinates.longitude='%s', country='%s', info='%s', isSuggestable='%s']",
            id, name, slug, coordinates.getLatitude(), coordinates.getLongitude(), country, info, isSuggestable);
   }
}
