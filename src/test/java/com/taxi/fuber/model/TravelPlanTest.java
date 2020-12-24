package com.taxi.fuber.model;

import com.taxi.fuber.model.dto.CustomerRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TravelPlanTest {
    private TravelPlan travelPlan;
    private Location bengaluru;
    private Location chennai;
    private Car pinkCarOne;
    private Car pinkCarTwo;

    @Test
    public void shouldReturnTripWhenCarIsAvailableForCustomerRequest() {
        LocalDateTime bookingTime = LocalDateTime.now();
        CustomerRequest customerRequest = new CustomerRequest(bengaluru, chennai, bookingTime, Color.PINK);
        Fleet fleet = loadFleet();
        travelPlan = new TravelPlan();

        when(fleet.getAnNearestAvailableCar(eq(Color.PINK), eq(bookingTime), eq(bengaluru)))
                .thenReturn(pinkCarOne);

        Trip trip = travelPlan.createATrip(customerRequest, fleet);

        assertEquals(48.3, trip.getCharge());

    }

    private Fleet loadFleet() {
        bengaluru = new Location(12.972442, 77.580643);
        chennai = new Location(13.0827, 80.2707);
        pinkCarOne = new Car("ABC123", Color.PINK, bengaluru);
        pinkCarTwo = new Car("GHI789", Color.PINK, chennai);
        return new Fleet(new ArrayList<>(Arrays.asList(pinkCarOne, pinkCarTwo)));
    }
}