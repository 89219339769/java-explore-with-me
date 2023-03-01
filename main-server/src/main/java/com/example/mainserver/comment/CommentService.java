package com.example.mainserver.comment;

import com.example.mainserver.comment.model.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long userId, Long eventId);
}
