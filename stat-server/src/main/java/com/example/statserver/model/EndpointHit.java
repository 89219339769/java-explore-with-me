package com.example.statserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@Table(name = "endpointhit", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public
class EndpointHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String app;

    @Column(nullable = false)
    private String  uri;

    @Column(nullable = false)
    private String   ip;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

}





