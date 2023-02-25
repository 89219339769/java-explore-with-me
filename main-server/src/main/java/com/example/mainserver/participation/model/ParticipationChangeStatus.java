package com.example.mainserver.participation.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder


public class ParticipationChangeStatus {

    private List<Integer> requestIds;
    private final StatusRequest status;

    public ParticipationChangeStatus(List<Integer> requestIds, StatusRequest statusRequest) {
        this.requestIds = requestIds;
        this.status = statusRequest;
    }
}
