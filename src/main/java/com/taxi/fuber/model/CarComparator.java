package com.taxi.fuber.model;

import java.util.Comparator;

public class CarComparator<T> implements Comparator<Car> {
    private Location toLocation;

    public CarComparator(Location toLocation) {
        this.toLocation = toLocation;
    }

    @Override
    public int compare(Car thisCar, Car thatCar) {
        return Double.compare(thisCar.getLocation().distanceTo(toLocation),
                thatCar.getLocation().distanceTo(toLocation));
    }

}
