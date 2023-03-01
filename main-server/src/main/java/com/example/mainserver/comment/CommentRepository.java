package com.example.mainserver.comment;

import com.example.mainserver.comment.model.Comment;
import com.example.mainserver.participation.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
