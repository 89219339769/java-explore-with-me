package com.example.mainserver.comment;

import com.example.mainserver.comment.model.Comment;
import com.example.mainserver.comment.model.CommentDto;
import com.example.mainserver.comment.model.CommentMapper;
import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.event.model.State;
import com.example.mainserver.exceptions.CompilationNotFounfExeption;
import com.example.mainserver.user.UserRepository;
import com.example.mainserver.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mainserver.comment.Stat.*;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    private final EventRepository eventRepository;


    @Override
    public CommentDto createComment(CommentDto commentDto, Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));

        if (event.getState().equals(State.PENDING) && event.getState().equals(State.CANCELED))

            throw new RuntimeException("можно комментировать только одобренные события");

        commentDto.setUser(user);
        commentDto.setEvent(event);

        commentRepository.save(CommentMapper.toComment(commentDto));
        return commentDto;

    }

    @Override
    public Comment publishComment(Long userId, Long comentId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));

        Comment comment = commentRepository.findById(comentId)
                .orElseThrow(() -> new CompilationNotFounfExeption("comment with id = " + userId + " not found"));
        if (!comment.getState().equals(WAITING)) {
            throw new RuntimeException("Можно только публиковть комментарии со статусом  WAITING");
        }
        comment.setState(APPROVED);
        return commentRepository.save(comment);
    }

    @Override
    public Comment regectComment(Long userId, Long comentId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));

        Comment comment = commentRepository.findById(comentId)
                .orElseThrow(() -> new CompilationNotFounfExeption("comment with id = " + userId + " not found"));
        if (!comment.getState().equals(WAITING)) {
            throw new RuntimeException("Можно только отвргать комментарии со статусом  WAITING");
        }
        comment.setState(REJECTED);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByEventId(Long eventId) {
        return commentRepository.findAllByEventId(eventId);
    }
}
