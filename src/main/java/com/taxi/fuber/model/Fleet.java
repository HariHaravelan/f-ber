package com.taxi.fuber.model;

import java.time.LocalDateTime;
import java.util.List;

public class Fleet {

    private List<Car> cars;

    public Fleet(List<Car> cars) {
        this.cars = cars;
    }

    public Car getAnNearestAvailableCar(Color color, LocalDateTime tripStartTime, Location pickupLocation) {
        return this.cars.stream().sorted(new CarComparator<>(pickupLocation)).
                filter(car -> car.getColor().equals(color) && car.isAvailable(tripStartTime))
                .findFirst().orElse(null);
    }

    public void updateCarStatus(String plateNumber, LocalDateTime tripEndsAt, Location dropLocation) {
        this.cars.stream().filter(car -> plateNumber.equals(car.getPlateNumber())).forEach(car -> {
            car.updateCurrentLocation(dropLocation);
            car.updateNextAvailableAt(tripEndsAt);
        });
    }
}

