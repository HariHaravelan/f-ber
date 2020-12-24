package com.taxi.fuber.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class Location {
    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.1515;
    private static final double MILE_TO_KM = 1.609344;
    private static final int EARTH_DEGREE = 60;
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double distanceTo(Location location) {
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(location.latitude);
        double lon2 = Math.toRadians(location.longitude);

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double distanceInKms = EARTH_DEGREE * Math.toDegrees(angle) *
                STATUTE_MILES_PER_NAUTICAL_MILE * MILE_TO_KM;

        return distanceInKms;
    }

}


