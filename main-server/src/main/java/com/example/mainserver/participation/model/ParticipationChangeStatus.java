package com.example.mainserver.participation.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder


public class ParticipationChangeStatus {

   private List<Long> requestIds;
  private final   StatusRequest status;

    public ParticipationChangeStatus(List<Long> requestIds, StatusRequest statusRequest) {
        this.requestIds = requestIds;
        this.status = statusRequest;
    }
}
