package engine;

import model.Coordinates;

/**
 * Created by SDesby on 12/01/2016.
 */
public class DistanceCalculator {

    Coordinates coordinates1;
    Coordinates coordinates2;

    public DistanceCalculator(Coordinates coordinates1, Coordinates coordinates2)
    {
        this.coordinates1 = coordinates1;
        this.coordinates2 = coordinates2;
    }

    public DistanceCalculator(double latitude1, double longitude1, double latitude2, double longitude2) {
        coordinates1 = new Coordinates(latitude1, longitude1);
        coordinates2 = new Coordinates(latitude2, longitude2);
    }

    public double getDistance() {
        //source = http://www.movable-type.co.uk/scripts/latlong.html
        double earthRadius = 6371000.0;

        double userLatToRad = Math.toRadians(coordinates1.getLatitude());
        double trainStationLatToRad = Math.toRadians(coordinates2.getLatitude());

        double deltaLatitude = Math.toRadians(coordinates2.getLatitude() - coordinates1.getLatitude());
        double deltaLongitude = Math.toRadians(coordinates2.getLongitude() - coordinates1.getLongitude());

        double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)
                + Math.cos(userLatToRad) * Math.cos(trainStationLatToRad)
                * Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (earthRadius * c) / 1000; // en km
    }
}
