package com.example.mainserver.comment.controller;

import com.example.mainserver.comment.CommentService;
import com.example.mainserver.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping()
@AllArgsConstructor
public class CommentControllerPublic {
    private final CommentService commentService;

    @GetMapping("/event/{eventId}/comments")
    public List<Comment> getCommentByEventId(@PathVariable Long eventId) {
        return commentService.getCommentsByEventId(eventId);
    }

    //добваить пользователю возможность свой комент редактировать пока его не опубликовали и удалять

}
