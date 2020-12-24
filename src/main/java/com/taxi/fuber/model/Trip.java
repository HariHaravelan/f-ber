package com.taxi.fuber.model;

import java.time.LocalDateTime;

public class Trip {
    private LocalDateTime endsAt;
    private double distanceInKMs;

    public Trip(LocalDateTime endsAt, double distanceInKMs) {
        this.endsAt = endsAt;
        this.distanceInKMs = distanceInKMs;
    }

    public boolean isComplete(LocalDateTime time) {
        return endsAt.isBefore(time);
    }

    public double getCharge() {
        return 0;
    }
}
