package com.taxi.fuber.model;

import com.taxi.fuber.model.dto.CustomerRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripPlanTest {

    @Test
    public void shouldReturnTripWhenCarIsAvailableForCustomerRequest() {
        Location bengaluru = mock(Location.class);
        Location chennai = mock(Location.class);
        Fleet fleet = mock(Fleet.class);
        Car pinkCarOne = mock(Car.class);
        LocalDateTime bookingTime = LocalDateTime.now();
        CustomerRequest customerRequest = new CustomerRequest(bengaluru, chennai, bookingTime, Color.PINK);

        when(fleet.getAnNearestAvailableCar(eq(Color.PINK), eq(bookingTime), eq(bengaluru)))
                .thenReturn(pinkCarOne);
        when(pinkCarOne.getLocation()).thenReturn(bengaluru);
        when(bengaluru.distanceTo(eq(bengaluru))).thenReturn(0.0);
        when(bengaluru.distanceTo(eq(chennai))).thenReturn(300.0);
        when(pinkCarOne.getTimeTakenInMinutes(eq(300.0))).thenReturn(300.0);
        when(pinkCarOne.calculateTripCharge(eq(300.0))).thenReturn(900.0);

        Trip trip = new TripPlan().createATrip(customerRequest, fleet);

        assertEquals(900, trip.getCharge());
    }


    @Test
    public void shouldThrowRuntimeExceptionWithCustomMessage() {
        Location bengaluru = mock(Location.class);
        Location chennai = mock(Location.class);
        Fleet fleet = mock(Fleet.class);
        LocalDateTime bookingTime = LocalDateTime.now();
        CustomerRequest customerRequest = new CustomerRequest(bengaluru, chennai, bookingTime, Color.PINK);

        when(fleet.getAnNearestAvailableCar(eq(Color.PINK), eq(bookingTime), eq(bengaluru)))
                .thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class,
                () -> new TripPlan().createATrip(customerRequest, fleet));

        assertEquals("Cars are not available at this time, Please try again later"
                , exception.getMessage());
    }

}