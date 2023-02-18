package com.example.mainserver.location.model;

import com.example.mainserver.user.model.User;
import com.example.mainserver.user.model.UserDto;

public class LocationMapping {
    public static LocationDto toLocationDto(Location location) {
        return LocationDto
                .builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }


}
