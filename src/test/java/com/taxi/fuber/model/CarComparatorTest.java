package com.taxi.fuber.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CarComparatorTest {

    private Car pinkCarOne;
    private Car pinkCarTwo;
    private Car regularCarOne;
    private Car regularCarTwo;

    @Test
    public void shouldReturnCarsSortedByDistanceToGivenLocation() {
        Location mysore = new Location(12.2958, 76.6394);
        List<Car> cars = getCars();

        cars.sort(new CarComparator<>(mysore));

        assertArrayEquals(new Car[]{regularCarOne, pinkCarTwo, regularCarTwo, pinkCarOne}, cars.toArray(new Car[0]));
    }

    private List<Car> getCars() {
        Location bengaluru = new Location(12.972442, 77.580643);
        Location chennai = new Location(13.0827, 80.2707);
        Location kolkata = new Location(22.5726, 88.3639);
        Location mumbai = new Location(19.0760, 72.8777);
        pinkCarOne = new Car("ABC123", Color.PINK, kolkata);
        pinkCarTwo = new Car("GHI789", Color.PINK, chennai);
        regularCarOne = new Car("DEF456", Color.OTHERS, bengaluru);
        regularCarTwo = new Car("XYZ007", Color.OTHERS, mumbai);
        return new ArrayList<>(Arrays.asList(pinkCarOne, pinkCarTwo, regularCarOne, regularCarTwo));
    }

}