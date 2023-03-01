package com.example.mainserver.comment.model;

import com.example.mainserver.event.model.Event;
import com.example.mainserver.event.model.NewEventDto;
import com.example.mainserver.event.model.State;

import java.time.LocalDateTime;

import static com.example.mainserver.comment.Stat.WAITING;
import static com.example.mainserver.event.model.State.PENDING;

public class CommentMapper {


    public static Comment toComment(CommentDto commentDto) {
        Comment build = Comment
                .builder()

                .description(commentDto.getDescription())
                .createdOn(LocalDateTime.now())
                .state(WAITING)
                .createdOn(LocalDateTime.now())
                .user(commentDto.getUser())
                .event(commentDto.getEvent())
                .build();
        return build;
    }
}


