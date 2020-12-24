package com.taxi.fuber.model;

import java.time.LocalDateTime;

public class Trip {
    private Car car;
    private LocalDateTime bookingTime;
    private double distanceTraveled;
    private double timeTakenInMinutes;

    public Trip(Car car, LocalDateTime bookingTime, Location pickUpLocation, Location dropLocation) {
        this.car = car;
        this.bookingTime = bookingTime;
        this.distanceTraveled = this.car.getLocation().distanceTo(pickUpLocation)
                + pickUpLocation.distanceTo(dropLocation);
        this.timeTakenInMinutes = this.car.getTimeTakenInMinutes(this.distanceTraveled);
    }

    public double getCharge() {
        return this.car.calculateTripCharge(timeTakenInMinutes);
    }

    public LocalDateTime getTripEndsAt() {
        return bookingTime.plusMinutes((long) timeTakenInMinutes);
    }
}
