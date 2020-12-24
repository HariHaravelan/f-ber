package com.taxi.fuber.model;

import com.taxi.fuber.model.dto.CustomerRequest;

public class TripPlan {

    public Trip createATrip(CustomerRequest customerRequest, Fleet fleet) {
        Car car = fleet.getAnNearestAvailableCar(customerRequest.getColorPreference()
                , customerRequest.getBookingTime()
                , customerRequest.getCurrentLocation());
        if (car != null) {
            Trip trip = new Trip(car, customerRequest.getBookingTime(),
                    customerRequest.getCurrentLocation(), customerRequest.getDropLocation());

            fleet.updateCarStatus(car, trip.getTripEndsAt());
            return trip;
        } else {
            throw new RuntimeException("Cars are not available at this time, Please try again later");
        }
    }


}
