package com.taxi.fuber.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FleetTest {

    private Car dzire;
    private Car beetle;
    private Car polo;

    @BeforeEach
    public void setUp() {
        dzire = mock(Car.class);
        beetle = mock(Car.class);
        polo = mock(Car.class);
    }

    @Test
    public void shouldReturnNearestAvailableCarWhenCarIsAvailableInGivenColorAndTime() {
        List<Car> cars = getCars();
        Fleet fleet = new Fleet(cars);

        Car car = fleet.getAnNearestAvailableCar(Color.PINK, LocalDateTime.now(), new Location(0, 0));

        assertEquals(Color.PINK, car.getColor());
        assertEquals("ABC123", car.getPlateNumber());
    }

    @Test
    public void shouldUpdateCarCurrentLocationAndNextAvailableWhenCarStartsTrip() {
        List<Car> cars = getCars();
        Fleet fleet = new Fleet(cars);
        Location dropLocation = new Location(12, 70);

        LocalDateTime tripEndsAt = LocalDateTime.now().plusMinutes(10);
        fleet.updateCarStatus("ABC123", tripEndsAt, dropLocation);

        verify(dzire, times(1)).updateCurrentLocation(eq(dropLocation));
        verify(dzire, times(1)).updateNextAvailableAt(eq(tripEndsAt));
    }

    private List<Car> getCars() {
        when(dzire.getColor()).thenReturn(Color.PINK);
        when(beetle.getColor()).thenReturn(Color.PINK);
        when(polo.getColor()).thenReturn(Color.OTHERS);
        when(dzire.getPlateNumber()).thenReturn("ABC123");
        when(dzire.getLocation()).thenReturn(new Location(1, 2));
        when(beetle.getLocation()).thenReturn(new Location(10, 20));
        when(polo.getLocation()).thenReturn(new Location(2, 2));
        when(dzire.isAvailable(any(LocalDateTime.class))).thenReturn(true);
        when(beetle.isAvailable(any(LocalDateTime.class))).thenReturn(true);
        when(polo.isAvailable(any(LocalDateTime.class))).thenReturn(true);

        return new ArrayList<>(Arrays.asList(dzire, beetle, polo));
    }

}