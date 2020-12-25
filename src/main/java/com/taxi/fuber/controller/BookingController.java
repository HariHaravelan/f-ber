package com.taxi.fuber.controller;

import com.taxi.fuber.model.Fleet;
import com.taxi.fuber.model.Trip;
import com.taxi.fuber.model.TripPlan;
import com.taxi.fuber.model.dto.CustomerRequest;
import com.taxi.fuber.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private FleetService fleetService;
    private Fleet fleet;

    @PostMapping
    public ResponseEntity<Trip> bookACab(@RequestBody CustomerRequest customerRequest) {
        fleet = fleetService.getFleet();

        return new TripPlan().createATrip(customerRequest, fleet).map(trip ->
                new ResponseEntity<>(trip, HttpStatus.CREATED)
        ).orElse(new ResponseEntity<>(HttpStatus.OK));
    }
}
