package com.taxi.fuber.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripTest {

    @Test
    public void shouldReturnTrueWhenTripEndedBeforeGivenTime() {
        LocalDateTime tripStartTime = LocalDateTime.now();
        LocalDateTime tenMinutesBeforeNow = LocalDateTime.now().minusMinutes(10);

        Trip trip = new Trip(tenMinutesBeforeNow, 10);

        boolean completed = trip.isComplete(tripStartTime);

        assertTrue(completed);
    }

}