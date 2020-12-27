package com.taxi.fuber.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taxi.fuber.model.Color;
import com.taxi.fuber.model.Location;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonSerialize
public class CarDto {
    private DateTimeFormatter dateTimeDisplayFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss a");
    @JsonProperty
    private String plateNumber;
    @JsonProperty
    private Color color;
    @JsonProperty
    private Location currentLocation;
    @JsonProperty
    private boolean available;
    @JsonProperty
    private String nextAvailableAt;

    public CarDto(String plateNumber, Color color, Location currentLocation, boolean available, LocalDateTime nextAvailableAt) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.currentLocation = currentLocation;
        this.available = available;
        this.nextAvailableAt = dateTimeDisplayFormat.format(nextAvailableAt);
    }
}
