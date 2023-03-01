package com.example.mainserver.user.model;


public class UserMapping {



    public static UserDto toUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
