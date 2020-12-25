package com.taxi.fuber.model.dto;

import com.taxi.fuber.model.Color;
import com.taxi.fuber.model.Location;

import java.time.LocalDateTime;

public class CustomerRequest {
    private Location currentLocation;
    private Location dropLocation;
    private LocalDateTime bookingTime;
    private Color colorPreference;

    public CustomerRequest(Location currentLocation, Location dropLocation, LocalDateTime bookingTime, Color colorPreference) {
        this.currentLocation = currentLocation;
        this.dropLocation = dropLocation;
        this.bookingTime = bookingTime;
        this.colorPreference = colorPreference;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Location getDropLocation() {
        return dropLocation;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public Color getColorPreference() {
        return colorPreference;
    }
}
