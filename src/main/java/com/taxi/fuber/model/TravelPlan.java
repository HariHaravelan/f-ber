package com.taxi.fuber.model;

import com.taxi.fuber.model.dto.CustomerRequest;

import java.time.LocalDateTime;

public class TravelPlan {

    public Trip createATrip(CustomerRequest customerRequest, Fleet fleet) {
        Car car = fleet.getAnNearestAvailableCar(customerRequest.getColorPreference()
                , LocalDateTime.now()
                , customerRequest.getCurrentLocation());
        if (car != null) {
            double distanceToTravel = getDistanceToTravel(car,
                    customerRequest.getCurrentLocation(), customerRequest.getDestination());
            LocalDateTime tripEndsAt = LocalDateTime.now()
                    .plusMinutes(car.getTimeTakenInMinutes(distanceToTravel));

            Trip trip = new Trip(tripEndsAt, distanceToTravel);
            car.updateTrip(trip);
            fleet.updateCarStatus(car);
            return trip;
        } else {
            throw new RuntimeException("Cars are not available at this time, Please try again later");
        }
    }

    private double getDistanceToTravel(Car car, Location pickUpLocation,
                                       Location customerDestination) {
        return car.getLocation().distanceTo(pickUpLocation)
                + pickUpLocation.distanceTo(customerDestination);
    }
}
