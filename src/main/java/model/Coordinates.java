package model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by SDesby on 12/01/2016.
 */
public class Coordinates {

    @XmlElement(
          name = "latitude",
          required = true
    )
    private final double latitude;

    @XmlElement(
          name = "longitude",
          required = true
    )
    private final double longitude;


    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinates(String latitude, String longitude) {
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }

}
