package com.taxi.fuber.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.taxi.fuber.model.Color;
import com.taxi.fuber.model.Location;

@JsonSerialize
public class CarResponse {
    @JsonProperty
    private String plateNumber;
    @JsonProperty
    private Color color;
    @JsonProperty
    private Location currentLocation;
    @JsonProperty
    private boolean available;

    public CarResponse(String plateNumber, Color color, Location currentLocation, boolean available) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.currentLocation = currentLocation;
        this.available = available;
    }
}
