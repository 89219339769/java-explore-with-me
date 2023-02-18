package com.example.mainserver.user.model;

import com.example.mainserver.event.model.Event;
import com.example.mainserver.event.model.EventDto;

public class UserMapping {



    public static UserDto toUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
