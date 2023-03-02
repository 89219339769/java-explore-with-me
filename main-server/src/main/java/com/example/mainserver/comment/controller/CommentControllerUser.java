package com.example.mainserver.comment.controller;


import com.example.mainserver.comment.CommentService;
import com.example.mainserver.comment.model.Comment;
import com.example.mainserver.comment.model.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

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

    @PatchMapping("/{eventId}")
    public CommentDto patchComment(@Valid @RequestBody CommentDto commentDto,
                                   @Positive @PathVariable Long userId,
                                   @Positive @PathVariable Long eventId) {
        log.info("create comment by user {}", userId);
        return commentService.patchComment(commentDto, userId, eventId);
    }

    @DeleteMapping("/{eventId}")
    public void deleteComment(
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId) {
        log.info("create comment by user {}", userId);
        commentService.deleteComment(userId, eventId);
    }

    @GetMapping("/{eventId}")
    public Comment getComment(
            @Positive @PathVariable Long userId,
            @Positive @PathVariable Long eventId) {
        log.info("create comment by user {}", userId);
        return commentService.getComment(userId, eventId);
    }


    @GetMapping()
    public List<Comment> getComments(
            @Positive @PathVariable Long userId) {
        log.info("create comment by user {}", userId);
        return commentService.getCommentsByUser(userId);
    }


}