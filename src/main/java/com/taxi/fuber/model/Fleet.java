package com.taxi.fuber.model;

import java.time.LocalDateTime;
import java.util.List;

public class Fleet {

    private List<Car> cars;

    public Fleet(List<Car> cars) {
        this.cars = cars;
    }

    public Car getAnAvailableCar(Color color, LocalDateTime tripStartTime, Location location) {
        return null;
    }
}

