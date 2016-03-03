package dao;

public class TrainStationPOJO {

   private String id;

   private String name;
   private String slug;

   private String longitude;

   private String latitude;
   private String country;
   private String info;

   private String is_suggestable;

   public TrainStationPOJO(String id, String name, String slug, String longitude, String latitude, String country, String info, String is_suggestable) {
      this.id = id;
      this.name = name;
      this.slug = slug;
      this.longitude = longitude;
      this.latitude = latitude;
      this.country = country;
      this.info = info;
      this.is_suggestable = is_suggestable;
   }

   public String getId() {
      return id;
   }

   public String getIs_suggestable() {
      return is_suggestable;
   }

   public String getName() {
      return name;
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

   public String getLongitude() {
      return longitude;
   }

   public String getLatitude() {
      return latitude;
   }
}
