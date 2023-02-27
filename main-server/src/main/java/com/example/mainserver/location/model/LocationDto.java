package com.example.mainserver.location.model;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private float lat;

    private float lon;
}
