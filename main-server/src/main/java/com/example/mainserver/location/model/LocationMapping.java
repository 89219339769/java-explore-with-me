package com.example.mainserver.location.model;


public class LocationMapping {
    public static LocationDto toLocationDto(Location location) {
        return LocationDto
                .builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}
