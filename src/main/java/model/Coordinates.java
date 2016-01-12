package model;

/**
 * Created by SDesby on 12/01/2016.
 */
public class Coordinates {
    private double latitude;
    private double longitude;

    public Coordinates() {
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
