package com.taxi.fuber.model;

import java.util.ArrayList;
import java.util.List;

public class TripLog {
    private List<Trip> trips;

    public TripLog() {
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }
}
