package com.example.statserver.model;

import lombok.*;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class ViewStats {

    private String app;
    private String uri;

    private Integer hits;

}
