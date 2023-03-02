package com.example.mainserver.comment.controller;

import com.example.mainserver.comment.CommentService;
import com.example.mainserver.comment.model.Comment;
import com.example.mainserver.event.model.EventDtoShort;
import eventcl.EndpointHit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("event/{eventId}/comments")
@AllArgsConstructor
public class CommentControllerPublic {
    private final CommentService commentService;


    @GetMapping
    public List<Comment> getCommentByEventId(@PathVariable Long eventId,
                                             @RequestParam(defaultValue = "0") int from,
                                             @RequestParam(defaultValue = "10") int size
    ) {
        return commentService.getCommentsByEventId(eventId, from, size);
    }
}
