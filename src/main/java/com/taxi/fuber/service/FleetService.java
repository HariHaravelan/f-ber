package com.taxi.fuber.service;


import com.taxi.fuber.model.Car;
import com.taxi.fuber.model.Color;
import com.taxi.fuber.model.Fleet;
import com.taxi.fuber.model.Location;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FleetService {

    Location bengaluru = new Location(12.972442, 77.580643);
    Location chennai = new Location(13.0827, 80.2707);
    Location kolkata = new Location(22.5726, 88.3639);
    Location mumbai = new Location(19.0760, 72.8777);
    Location mysuru = new Location(12.2958, 76.6394);
    Car dzire = new Car("ABC123", Color.OTHERS, bengaluru);
    Car nonPinkBeetle = new Car("KLM123", Color.OTHERS, chennai);
    Car beetle = new Car("DEF123", Color.PINK, chennai);
    Car miniCooper = new Car("GHI123", Color.OTHERS, kolkata);
    Car innova = new Car("XYZ123", Color.OTHERS, mumbai);

    public Fleet getFleet() {
        return new Fleet(Arrays.asList(dzire, beetle, miniCooper, innova, nonPinkBeetle));
    }
}
