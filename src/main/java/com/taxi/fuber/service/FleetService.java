package com.taxi.fuber.service;


import com.taxi.fuber.model.Car;
import com.taxi.fuber.model.Color;
import com.taxi.fuber.model.Fleet;
import com.taxi.fuber.model.Location;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FleetService {

    private Fleet fleet;

    @PostConstruct
    private void setUp() {
        fleet = new Fleet(loadCarDetails());
    }

    public Fleet getFleet() {
        return this.fleet;
    }

    private List<Car> loadCarDetails() {
        try {
            List<Car> cars = new ArrayList<>();
            File csvFile = new ClassPathResource("data.txt").getFile();
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    cars.add(buildCar(line));
                }
            }
            return cars;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Car buildCar(String line) {
        String[] lineParts = line.split(",");
        Location location = new Location(Double.parseDouble(lineParts[1]),
                Double.parseDouble(lineParts[2]));
        Color color = new Random().nextInt() % 3 == 0 ? Color.PINK : Color.OTHERS;
        return new Car(buildPlateNumber(lineParts[0]), color, location);
    }

    private String buildPlateNumber(String city) {
        int middleIndex = city.length() / 2;
        int numberPart = (int) (Math.random() * 1890);
        return (city.substring(0, 1) +
                city.substring(middleIndex, middleIndex + 1)
                + city.substring(city.length() - 1) + numberPart)
                .toUpperCase();
    }

}
