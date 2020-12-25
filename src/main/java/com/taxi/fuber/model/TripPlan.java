package com.taxi.fuber.model;

import com.taxi.fuber.model.dto.CustomerRequest;

import java.util.Optional;

public class TripPlan {

    public Optional<Trip> createATrip(CustomerRequest customerRequest, Fleet fleet) {
        Car car = fleet.getAnNearestAvailableCar(customerRequest.getColorPreference()
                , customerRequest.getBookingTime()
                , customerRequest.getCurrentLocation());
        if (car != null) {
            Trip trip = new Trip(car, customerRequest.getBookingTime(),
                    customerRequest.getCurrentLocation(), customerRequest.getDropLocation());
            fleet.updateCarStatus(car.getPlateNumber(), trip.getTripEndsAt(), customerRequest.getDropLocation());
            return Optional.of(trip);
        }
        return Optional.empty();
    }


}
