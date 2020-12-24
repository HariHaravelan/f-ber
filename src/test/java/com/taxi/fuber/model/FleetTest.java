package com.taxi.fuber.model;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FleetTest {


    @Test
    public void shouldReturnNearestAvailableCarWhenCarIsAvailableInGivenColorAndTime() {
        List<Car> cars = getCars();
        Fleet fleet = new Fleet(cars);

        Car car = fleet.getAnAvailableCar(Color.PINK, LocalDateTime.now(), new Location(0, 0));

        assertEquals(Color.PINK, car.getColor());
        assertEquals("ABC123", car.getPlateNumber());
    }

    private List<Car> getCars() {
        Car pinkCarOne = new Car("ABC123", Color.PINK, new Location(1, 1));
        Car pinkCarTwo = new Car("GHI789", Color.PINK, new Location(10, 11));
        Car regularCar = new Car("DEF456", Color.OTHERS, new Location(2, 2));
        return new ArrayList<>(Arrays.asList(new Car[]{pinkCarOne, pinkCarTwo, regularCar}));
    }

}