package com.taxi.fuber;

import com.taxi.fuber.model.*;
import com.taxi.fuber.model.dto.CustomerRequest;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FuberApplicationTests {
    private Location bengaluru = new Location(12.972442, 77.580643);
    private Location chennai = new Location(13.0827, 80.2707);
    private Location kolkata = new Location(22.5726, 88.3639);
    private Location mumbai = new Location(19.0760, 72.8777);
    private Location mysuru = new Location(12.2958, 76.6394);
    private Car dzire = new Car("ABC123", Color.OTHERS, bengaluru);
    private Car nonPinkBeetle = new Car("KLM123", Color.OTHERS, chennai);
    private Car beetle = new Car("DEF123", Color.PINK, chennai);
    private Car miniCooper = new Car("GHI123", Color.OTHERS, kolkata);
    private Car innova = new Car("XYZ123", Color.OTHERS, mumbai);

    @Test
    public void integrationTestOne() {
        List<Car> cars = Arrays.asList(dzire, beetle, miniCooper, innova, nonPinkBeetle);
        Fleet fleet = new Fleet(cars);

        LocalDateTime firstBookingTime = LocalDateTime.now();
        LocalDateTime secondBookingTime = LocalDateTime.now().plusMinutes(10);

        LocalDateTime expectedChennaiBengaluruMysuruTripEndsAt = LocalDateTime.now().plusMinutes(417);
        LocalDateTime expectedBengaluruBengaluruMysuruTripEndsAt = LocalDateTime.now().plusMinutes(136);
        LocalDateTime expectedMumbaiBengaluruMysuruTripEndsAt = LocalDateTime.now().plusMinutes(980);
        LocalDateTime expectedKolkataBengaluruMysuruTripEndsAt = LocalDateTime.now().plusMinutes(1697);

        CustomerRequest rideOnAPinkCarBengaluruToMysuru
                = new CustomerRequest(bengaluru, mysuru, firstBookingTime, Color.PINK);
        CustomerRequest rideOnANonPinkCarBengaluruToMysuru
                = new CustomerRequest(bengaluru, mysuru, secondBookingTime, Color.OTHERS);

        Trip chennaiBengaluruMysuruTripOnPinkBeetle = new TripPlan().createATrip(rideOnAPinkCarBengaluruToMysuru, fleet).get();
        assertTrue(new TripPlan().createATrip(rideOnAPinkCarBengaluruToMysuru, fleet).isEmpty());

        Trip bengaluruBengaluruMysuruTripOnNonPinkDzire = new TripPlan().createATrip(rideOnANonPinkCarBengaluruToMysuru, fleet).get();
        Trip chennaiBengaluruMysuruTripOnNonPinkBeetle = new TripPlan().createATrip(rideOnANonPinkCarBengaluruToMysuru, fleet).get();
        Trip mumbaiBengaluruMysuruTripOnNonPinkInnova = new TripPlan().createATrip(rideOnANonPinkCarBengaluruToMysuru, fleet).get();
        Trip kolkataBengaluruMysuruTripOnNonPinkMiniCooper = new TripPlan().createATrip(rideOnANonPinkCarBengaluruToMysuru, fleet).get();

        assertEquals("1256", chennaiBengaluruMysuruTripOnPinkBeetle.getRoundedChargeAmount());
        assertEquals(0,
                Duration.between(expectedChennaiBengaluruMysuruTripEndsAt, chennaiBengaluruMysuruTripOnPinkBeetle.getTripEndsAt()).getSeconds(), 1);

        assertEquals("378", bengaluruBengaluruMysuruTripOnNonPinkDzire.getRoundedChargeAmount());
        assertEquals(0,
                Duration.between(expectedBengaluruBengaluruMysuruTripEndsAt, bengaluruBengaluruMysuruTripOnNonPinkDzire.getTripEndsAt()).getSeconds(), 1);


        assertEquals("1251", chennaiBengaluruMysuruTripOnNonPinkBeetle.getRoundedChargeAmount());
        assertEquals(0,
                Duration.between(expectedChennaiBengaluruMysuruTripEndsAt, chennaiBengaluruMysuruTripOnPinkBeetle.getTripEndsAt()).getSeconds(), 1);

        assertEquals("2910", mumbaiBengaluruMysuruTripOnNonPinkInnova.getRoundedChargeAmount());
        assertEquals(0,
                Duration.between(expectedMumbaiBengaluruMysuruTripEndsAt, mumbaiBengaluruMysuruTripOnNonPinkInnova.getTripEndsAt()).getSeconds(), 1);


        assertEquals("5061", kolkataBengaluruMysuruTripOnNonPinkMiniCooper.getRoundedChargeAmount());
        assertEquals(0,
                Duration.between(expectedKolkataBengaluruMysuruTripEndsAt, kolkataBengaluruMysuruTripOnNonPinkMiniCooper.getTripEndsAt()).getSeconds(), 1);


        assertTrue(new TripPlan().createATrip(rideOnANonPinkCarBengaluruToMysuru, fleet).isEmpty());

    }

}
