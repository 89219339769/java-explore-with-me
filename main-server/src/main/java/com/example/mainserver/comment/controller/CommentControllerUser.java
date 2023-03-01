package com.example.mainserver.comment.controller;


import com.example.mainserver.comment.CommentService;
import com.example.mainserver.comment.model.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/comments")

public class CommentControllerUser {
    private final CommentService commentService;

    public CommentControllerUser(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{eventId}")
    public CommentDto createComment(@Valid @RequestBody CommentDto commentDto,
                                    @Positive @PathVariable Long userId,
                                    @Positive @PathVariable Long eventId) {
        log.info("create comment by user {}", userId);
        return commentService.createComment(commentDto, userId, eventId);
    }

    //Добавить патч коммента, но его можно делать только пока админ не опубликует коммент
    //Добавить удаление коммента, но его можно делать только пока админ не опубликует коммент


}