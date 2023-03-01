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
}
