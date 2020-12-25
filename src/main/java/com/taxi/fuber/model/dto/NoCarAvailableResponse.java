package com.taxi.fuber.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class NoCarAvailableResponse {
    @JsonProperty("message")
    private String message;

    public NoCarAvailableResponse(String message) {
        this.message = message;
    }
}
