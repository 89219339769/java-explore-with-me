package com.example.mainserver.participation.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationListDto {
    private  List<ParticipationDto> confirmedRequests;

    private  List<ParticipationDto> rejectedRequests;
}