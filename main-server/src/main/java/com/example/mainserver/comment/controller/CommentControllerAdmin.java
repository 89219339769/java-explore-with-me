package com.example.mainserver.comment.controller;


import com.example.mainserver.comment.CommentService;
import com.example.mainserver.comment.model.Comment;
import com.example.mainserver.event.model.EventDto;
import com.example.mainserver.event.model.UpdateEventAdminRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/{userId}/comments/{comentId}")
public class CommentControllerAdmin {

    private final CommentService commentService;

    public CommentControllerAdmin(CommentService commentService) {
        this.commentService = commentService;
    }

    @PatchMapping("/approve")
    public Comment publishEventAdmin(@PathVariable Long userId, @PathVariable Long comentId) {
        log.info("publish comment with id {} by admin", comentId);
        return commentService.publishComment(userId, comentId );
    }


    @PatchMapping("/regect")
    public Comment regectEventAdmin(@PathVariable Long userId, @PathVariable Long comentId) {
        log.info("publish comment with id {} by admin", comentId);
        return commentService.regectComment(userId, comentId );
    }


}
