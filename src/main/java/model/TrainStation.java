package model;

public class TrainStation {

   private String id;

   private String name;
   private String slug;

   private String longitude;

   private String latitude;
   private String country;
   private String info;
   public TrainStation() {
   }

   public TrainStation(String id, String name, String slug, String longitude, String latitude, String country, String info) {
      this.id = id;
      this.name = name;
      this.slug = slug;
      this.longitude = longitude;
      this.latitude = latitude;
      this.country = country;
      this.info = info;
   }

   public TrainStation(TrainStation trainStation) {
      this.longitude = trainStation.getLongitude();
      this.latitude = trainStation.getLatitude();
      this.id = trainStation.getId();
      this.name = trainStation.getName();
      this.slug = trainStation.getSlug();
      this.country = trainStation.getCountry();
      this.info = trainStation.getInfo();
   }

   @Override
   public String toString() {
      return String.format(
            "model.TrainStation[id=%s, name='%s', slug='%s', coordinates.longitude='%s', coordinates.latitude='%s', country='%s', info='%s']",
            id, name, slug, longitude, latitude, country, info);
   }

   public String getId() {
      return id;
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
