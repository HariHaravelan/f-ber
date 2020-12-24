package com.taxi.fuber.model;

import java.time.LocalDateTime;

public class Trip {
    private LocalDateTime endsAt;
    private long distanceInKMs;

    public Trip(LocalDateTime endsAt, long distanceInKMs) {
        this.endsAt = endsAt;
        this.distanceInKMs = distanceInKMs;
    }

    public boolean isComplete(LocalDateTime time) {
        return endsAt.isBefore(time);
    }
}
