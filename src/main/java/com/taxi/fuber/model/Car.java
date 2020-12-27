package com.taxi.fuber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

@JsonSerialize
public class Car {
    private String plateNumber;
    private Color color;
    private Location startPoint;
    private Location currentLocation;
    private double speedKmPh;
    private LocalDateTime nextAvailableAt;

    public Car(String plateNumber, Color color, Location location) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.currentLocation = location;
        this.speedKmPh = TripRule.STANDARD_SPEED_KM_PH;
        this.nextAvailableAt = LocalDateTime.now();
    }

    public Color getColor() {
        return this.color;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    @JsonIgnore
    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public boolean isAvailable(LocalDateTime time) {
        return nextAvailableAt.isBefore(time);
    }

    public double calculateTripCharge(double distanceOfTrip) {
        return getTimeTakenInMinutes(distanceOfTrip) * TripRule.CHARGE_PER_MINUTE +
                distanceOfTrip * TripRule.CHARGE_PER_KM + this.color.getAdditionalCharge();
    }

    public int getTimeTakenInMinutes(double distanceInKm) {
        return (int) ((distanceInKm / this.speedKmPh) * 60);
    }

    public void updateNextAvailableAt(LocalDateTime tripEndsAt) {
        this.nextAvailableAt = tripEndsAt;
    }

    public void updateCurrentLocation(Location dropLocation) {
        this.startPoint = this.currentLocation;
        this.currentLocation = dropLocation;
    }

    @JsonIgnore
    public LocalDateTime getNextAvailableAt() {
        return this.nextAvailableAt;
    }
}

