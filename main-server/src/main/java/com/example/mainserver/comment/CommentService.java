package com.example.mainserver.comment;

import com.example.mainserver.comment.model.Comment;
import com.example.mainserver.comment.model.CommentDto;


import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long userId, Long eventId);

    Comment publishComment(Long userId, Long comentId);

    Comment regectComment(Long userId, Long comentId);

    List<Comment> getCommentsByEventId(Long eventId);
}
