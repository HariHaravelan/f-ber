package com.taxi.fuber.model.dto;

import com.taxi.fuber.model.Color;
import com.taxi.fuber.model.Location;

import java.time.LocalDateTime;

public class CustomerRequest {
    private Location currentLocation;
    private Location destination;
    private LocalDateTime bookingTime;
    private Color colorPreference;

    public CustomerRequest(Location currentLocation, Location destination, LocalDateTime bookingTime, Color colorPreference) {
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.bookingTime = bookingTime;
        this.colorPreference = colorPreference;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Location getDestination() {
        return destination;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public Color getColorPreference() {
        return colorPreference;
    }
}
