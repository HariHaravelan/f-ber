package com.taxi.fuber.controller;

import com.taxi.fuber.model.Fleet;
import com.taxi.fuber.model.TripLog;
import com.taxi.fuber.model.TripPlan;
import com.taxi.fuber.model.dto.CustomerRequest;
import com.taxi.fuber.model.dto.NoCarAvailableResponse;
import com.taxi.fuber.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private static final String BOOKING_NOT_POSSIBLE = "We are out of Taxis at this moment, Can you please try again!";
    @Autowired
    private FleetService fleetService;
    private Fleet fleet;
    private TripLog tripLog;

    @PostConstruct
    public void setUp() {
        tripLog = new TripLog();
        fleet = fleetService.getFleet();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity bookACab(@RequestBody CustomerRequest customerRequest) {
        return new TripPlan().createATrip(customerRequest, fleet).map(trip -> {
            this.tripLog.addTrip(trip);
            return new ResponseEntity<>(trip, HttpStatus.CREATED);
        }).orElse(new ResponseEntity(new NoCarAvailableResponse(BOOKING_NOT_POSSIBLE), HttpStatus.OK));
    }
}
