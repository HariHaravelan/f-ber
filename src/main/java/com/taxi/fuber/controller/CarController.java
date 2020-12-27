package com.taxi.fuber.controller;

import com.taxi.fuber.model.Car;
import com.taxi.fuber.model.dto.CarDto;
import com.taxi.fuber.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
@CrossOrigin
public class CarController {
    @Autowired
    private FleetService fleetService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CarDto>> getCars() {
        return new ResponseEntity<>(fleetService.getFleet()
                .getCars().stream().map(this::toCarResponse).collect(Collectors.toList()), HttpStatus.OK);
    }

    private CarDto toCarResponse(Car car) {
        return new CarDto(car.getPlateNumber(), car.getColor(), car.getCurrentLocation(),
                car.isAvailable(LocalDateTime.now()), car.getNextAvailableAt());
    }
}
