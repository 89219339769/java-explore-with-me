package com.example.mainserver.comment.model;

import com.example.mainserver.comment.Stat;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Enumerated(EnumType.STRING)
    private Stat state;
}

