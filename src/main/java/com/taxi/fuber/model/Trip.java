package com.taxi.fuber.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static com.taxi.fuber.model.TripRule.DOGE_COINS;

@JsonSerialize
public class Trip {
    @JsonProperty("car")
    private Car car;
    @JsonProperty("bookedAt")
    private LocalDateTime bookedAt;
    @JsonProperty("distance")
    private double distanceTraveled;
    @JsonProperty("timeTakenToReachDestination")
    private double timeTakenInMinutes;
    @JsonProperty("timeTakenToReachCustomer")
    private double timeTakenToPickupLocation;

    private static DecimalFormat CHARGE_AMOUNT_FORMAT = new DecimalFormat("#.##");

    public Trip(Car car, LocalDateTime bookedAt, Location pickUpLocation, Location dropLocation) {
        this.car = car;
        this.bookedAt = bookedAt;
        this.timeTakenToPickupLocation = this.car.getLocation().distanceTo(pickUpLocation);
        this.distanceTraveled = this.timeTakenToPickupLocation + pickUpLocation.distanceTo(dropLocation);
        this.timeTakenInMinutes = this.car.getTimeTakenInMinutes(this.distanceTraveled);
    }

    @JsonGetter("charge")
    public String getRoundedChargeAmount() {
        CHARGE_AMOUNT_FORMAT.setRoundingMode(RoundingMode.DOWN);
        return CHARGE_AMOUNT_FORMAT.format(getCharge()) + " " + DOGE_COINS;
    }

    private double getCharge() {
        return this.car.calculateTripCharge(timeTakenInMinutes);
    }

    public LocalDateTime getTripEndsAt() {
        return bookedAt.plusMinutes((long) timeTakenInMinutes);
    }
}
