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
         name = "howbig",
         required = true
   )
   private final String howbig;

   @XmlElement(
         name = "coordinates",
         required = true
   )
   private final Coordinates coordinates;
   @XmlElement(
         name = "postalCode",
         required = true
   )
   private final String postalCode;
   @XmlElement(
         name = "city",
         required = true
   )
   private final String city;

   @XmlElement(
         name = "department",
         required = true
   )
   private final String department;

   @XmlElement(
         name = "region",
         required = true
   )
   private final String region;

   public TrainStation() {
      this.id = "";
      this.name = "";
      this.howbig = "";
      this.coordinates = new Coordinates(0.0, 0.0);
      this.postalCode = "";
      this.city = "";
      this.department = "";
      this.region = "";
   }

   public TrainStation(TrainStationPOJO trainStationPOJO) {
      this.id = trainStationPOJO.getId();
      this.name = trainStationPOJO.getName();
      this.howbig = trainStationPOJO.getHowbig();
      this.coordinates = new Coordinates(trainStationPOJO.getLatitude(), trainStationPOJO.getLongitude());
      this.postalCode = trainStationPOJO.getPostalCode();
      this.city = trainStationPOJO.getCity();
      this.department = trainStationPOJO.getDepartment();
      this.region = trainStationPOJO.getRegion();
   }

   public TrainStation(String id, String name, String howbig, String latitude, String longitude, String postalCode, String city, String department, String region) {
      this.id = id;
      this.name = name;
      this.howbig = howbig;
      this.coordinates = new Coordinates(latitude, longitude);
      this.postalCode = postalCode;
      this.city = city;
      this.department = department;
      this.region = region;
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

   public String getHowbig() {
      return howbig;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public String getCity() {
      return city;
   }

   public String getDepartment() {
      return department;
   }

   public String getRegion() {
      return region;
   }

   @Override
   public String toString() {
      return "TrainStation{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", howbig='" + howbig + '\'' +
            ", coordinates=" + coordinates +
            ", city='" + city + '\'' +
            ", department='" + department + '\'' +
            ", region='" + region + '\'' +
            '}';
   }
}
