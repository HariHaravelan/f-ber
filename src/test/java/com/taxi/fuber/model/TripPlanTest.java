package com.taxi.fuber.model;

import com.taxi.fuber.model.dto.CustomerRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
        doNothing().when(fleet).updateCarStatus(eq("ABC123"), eq(bookingTime), eq(chennai));
        when(pinkCarOne.getLocation()).thenReturn(bengaluru);
        when(bengaluru.distanceTo(eq(bengaluru))).thenReturn(0);
        when(bengaluru.distanceTo(eq(chennai))).thenReturn(300);
        when(pinkCarOne.getTimeTakenInMinutes(eq(300.0))).thenReturn(300);
        when(pinkCarOne.calculateTripCharge(eq(300.0))).thenReturn(900.0);
        when(pinkCarOne.getPlateNumber()).thenReturn("ABC123");

        Trip trip = new TripPlan().createATrip(customerRequest, fleet).get();

        verify(fleet, times(1)).updateCarStatus(eq("ABC123"), eq(trip.getTripEndsAt()), eq(chennai));
        assertEquals("900 dogecoins", trip.getRoundedChargeAmount());
    }


    @Test
    public void shouldReturnEmptyTripWhenNoCarAvailable() {
        Location bengaluru = mock(Location.class);
        Location chennai = mock(Location.class);
        Fleet fleet = mock(Fleet.class);
        LocalDateTime bookingTime = LocalDateTime.now();
        CustomerRequest customerRequest = new CustomerRequest(bengaluru, chennai, bookingTime, Color.PINK);

        when(fleet.getAnNearestAvailableCar(eq(Color.PINK), eq(bookingTime), eq(bengaluru)))
                .thenReturn(null);

        assertTrue(new TripPlan().createATrip(customerRequest, fleet).isEmpty());
    }

}