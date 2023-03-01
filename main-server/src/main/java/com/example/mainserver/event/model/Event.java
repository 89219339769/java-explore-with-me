package com.example.mainserver.event.model;

import com.example.mainserver.category.model.Category;
import com.example.mainserver.location.model.Location;
import com.example.mainserver.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String annotation;

    @ManyToOne
    private Category category;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(length = 1000)
    private String description;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @ManyToOne
    private User initiator;

    @ManyToOne
    private Location location;

    @Column(nullable = false)
    private Boolean paid;

    @Column(name = "participant_limit")
    private Integer participantLimit;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private Boolean requestModeration;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(length = 255, nullable = false)
    private String title;

    @Builder.Default
    private Long views = 0L;

}