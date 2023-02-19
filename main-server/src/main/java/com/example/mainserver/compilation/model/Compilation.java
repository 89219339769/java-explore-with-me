package com.example.mainserver.compilation.model;

import com.example.mainserver.event.model.Event;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "compilations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    private Boolean pinned;

    @ManyToMany
    @JoinTable(name = "event_and_compilation",
            joinColumns = {@JoinColumn(name = "compilation_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> events;
}
