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
    @JsonProperty
    private Car car;
    private double distanceToPickupPoint;
    private double distanceFromPickUpToDestination;
    private double totalTimeTaken;
    private double timeTakenToPickupLocation;
    @JsonProperty
    private Location pickUpLocation;
    @JsonProperty
    private Location dropLocation;
    @JsonProperty
    private LocalDateTime bookedAt;

    private static DecimalFormat CHARGE_AMOUNT_FORMAT = new DecimalFormat("#.##");

    public Trip(Car car, LocalDateTime bookedAt, Location pickUpLocation, Location dropLocation) {
        this.car = car;
        this.bookedAt = bookedAt;
        this.pickUpLocation = pickUpLocation;
        this.dropLocation = dropLocation;
        this.distanceToPickupPoint = this.car.getCurrentLocation().distanceTo(pickUpLocation);
        this.timeTakenToPickupLocation = this.car.getTimeTakenInMinutes(distanceToPickupPoint);
        this.distanceFromPickUpToDestination = pickUpLocation.distanceTo(dropLocation);
        this.totalTimeTaken = this.car.getTimeTakenInMinutes(this.distanceToPickupPoint
                + this.distanceFromPickUpToDestination);
    }

    private double getCharge() {
        return this.car.calculateTripCharge(this.distanceToPickupPoint
                + this.distanceFromPickUpToDestination);
    }

    @JsonGetter("pickupAt")
    private LocalDateTime getPickupAt() {
        return bookedAt.plusMinutes((long) this.timeTakenToPickupLocation);
    }

    @JsonGetter("reachesDestinationAt")
    public LocalDateTime getTripEndsAt() {
        return bookedAt.plusMinutes((long) totalTimeTaken);
    }

    @JsonGetter("charge")
    public String getRoundedChargeAmount() {
        CHARGE_AMOUNT_FORMAT.setRoundingMode(RoundingMode.DOWN);
        return CHARGE_AMOUNT_FORMAT.format(getCharge()) + " " + DOGE_COINS;
    }
}
