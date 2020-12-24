package com.taxi.fuber.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {


    @Test
    public void shouldReturnDistanceToOtherLocation() {
        Location bengaluru = new Location(12.972442, 77.580643);
        Location chennai = new Location(13.0827, 80.2707);

        double distance = bengaluru.distanceTo(chennai);

        assertEquals(291, distance, 1);
    }

}