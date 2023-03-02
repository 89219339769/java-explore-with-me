package com.example.mainserver.comment.model;


import com.example.mainserver.event.EventRepository;
import com.example.mainserver.exceptions.CompilationNotFounfExeption;
import com.example.mainserver.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.mainserver.comment.Stat.WAITING;

@AllArgsConstructor
@Service
public class CommentMapper {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public Comment toComment(CommentDto commentDto) {
        Comment build = Comment
                .builder()

                .description(commentDto.getDescription())
                .createdOn(LocalDateTime.now())
                .state(WAITING)
                .createdOn(LocalDateTime.now())
                .user(userRepository.findById(commentDto.getUserId())
                        .orElseThrow(() -> new CompilationNotFounfExeption("user not found")))
                .event(eventRepository.findById(commentDto.getEventId())
                        .orElseThrow(() -> new CompilationNotFounfExeption("event not found")))
                .build();
        return build;
    }

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto
                .builder()
                .description(comment.getDescription())
                .createdOn(LocalDateTime.now())
                .userId(comment.getUser().getId())
                .eventId(comment.getEvent().getId())
                .build();

    }
}


