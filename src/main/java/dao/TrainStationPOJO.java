package dao;

public class TrainStationPOJO {

   private String id;
   private String name;
   private String howbig;
   private String latitude;
   private String longitude;
   private String postalCode;
   private String city;
   private String department;
   private String region;

   public TrainStationPOJO(String id, String name, String howbig, String longitude, String latitude, String postalCode, String city, String department, String region) {
      this.id = id;
      this.name = name;
      this.howbig = howbig;
      this.longitude = longitude;
      this.latitude = latitude;
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

   public String getLongitude() {
      return longitude;
   }

   public String getLatitude() {
      return latitude;
   }

   public String getCity() {
      return city;
   }

   public String getDepartment() {
      return department;
   }

   public String getHowbig() {
      return howbig;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public String getRegion() {
      return region;
   }
}
