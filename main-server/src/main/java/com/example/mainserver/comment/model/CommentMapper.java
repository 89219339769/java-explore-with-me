package com.example.mainserver.comment.model;



import java.time.LocalDateTime;

import static com.example.mainserver.comment.Stat.WAITING;


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

    public static CommentDto toCommentDto(Comment comment) {
        return   CommentDto
                .builder()
                .description(comment.getDescription())
                .createdOn(LocalDateTime.now())
                .createdOn(LocalDateTime.now())
                .user(comment.getUser())
                .event(comment.getEvent())
                .build();

    }



}


