package com.example.mainserver.participation.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto {
    private Long id;

    private String created;

    private Long event;

    private Long requester;

    private StatusRequest status;
}