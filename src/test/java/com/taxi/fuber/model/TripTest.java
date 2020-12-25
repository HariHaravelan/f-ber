package com.taxi.fuber.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripTest {

    @Test
    public void shouldReturnTripEndsAtFromCarTravelTime() {
        LocalDateTime bookingTime = LocalDateTime.now();
        LocalDateTime expectedTripEndsTime = bookingTime.plusMinutes(300);
        Location bengaluru = mock(Location.class);
        Location chennai = mock(Location.class);
        Car pinkCarOne = mock(Car.class);

        when(pinkCarOne.getTimeTakenInMinutes(eq(300.0))).thenReturn(300);
        when(pinkCarOne.getLocation()).thenReturn(bengaluru);
        when(bengaluru.distanceTo(eq(bengaluru))).thenReturn(0);
        when(bengaluru.distanceTo(eq(chennai))).thenReturn(300);

        Trip trip = new Trip(pinkCarOne, bookingTime, bengaluru, chennai);

        assertEquals(expectedTripEndsTime, trip.getTripEndsAt());
    }

    @Test
    public void shouldReturnChargeFromCarTravelChargeWithRoundedAmount() {
        LocalDateTime bookingTime = LocalDateTime.now();
        Location bengaluru = mock(Location.class);
        Location chennai = mock(Location.class);

        Car regularCar = mock(Car.class);

        when(regularCar.getTimeTakenInMinutes(eq(300.0))).thenReturn(300);
        when(regularCar.getColor()).thenReturn(Color.OTHERS);
        when(regularCar.getLocation()).thenReturn(bengaluru);
        when(bengaluru.distanceTo(eq(bengaluru))).thenReturn(0);
        when(bengaluru.distanceTo(eq(chennai))).thenReturn(300);
        when(regularCar.calculateTripCharge(eq(300.0))).thenReturn(900.0);

        Trip trip = new Trip(regularCar, bookingTime, bengaluru, chennai);

        assertEquals("900", trip.getRoundedChargeAmount());
    }

}