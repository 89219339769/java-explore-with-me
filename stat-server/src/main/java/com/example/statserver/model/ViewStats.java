package com.example.statserver.model;

import lombok.*;

import java.util.Objects;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor

public class ViewStats {

    private String app;
    private String uri;
    private Integer hits;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        ViewStats otherStat = (ViewStats) obj;

        if (Objects.equals(uri, otherStat.uri))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }
}
