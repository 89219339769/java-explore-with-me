package com.example.mainserver.comment;

import com.example.mainserver.comment.model.Comment;
import com.example.mainserver.comment.model.CommentDto;
import com.example.mainserver.comment.model.CommentMapper;
import com.example.mainserver.event.EventRepository;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.event.model.State;
import com.example.mainserver.exceptions.CompilationNotFounfExeption;
import com.example.mainserver.exceptions.RepeateCommentException;
import com.example.mainserver.exceptions.WrongPatchException;
import com.example.mainserver.user.UserRepository;
import com.example.mainserver.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mainserver.comment.Stat.*;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    private final EventRepository eventRepository;

    private final CommentMapper commentMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));


        Comment comment = commentRepository.findCommetnByserIdAnfEventId(userId, eventId);
        if (comment != null) {
            throw new RepeateCommentException("Можно коментировать событие только один раз");
        }
        if (commentDto.getDescription().isBlank()) {
            throw new RuntimeException("Необходимо добавить описание");
        }

        if (event.getState().equals(State.PENDING) && event.getState().equals(State.CANCELED))
            throw new WrongPatchException("можно комментировать только одобренные события");


        commentDto.setUserId(user.getId());
        commentDto.setEventId(event.getId());
        Comment comment1 = commentMapper.toComment(commentDto);

        commentRepository.save(commentMapper.toComment(commentDto));
        return commentDto;

    }

    @Override
    public Comment publishComment(Long userId, Long comentId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));

        Comment comment = commentRepository.findById(comentId)
                .orElseThrow(() -> new CompilationNotFounfExeption("comment with id = " + userId + " not found"));
        if (!comment.getState().equals(WAITING)) {
            throw new RepeateCommentException("Можно публиковать коммент только со статусом waiting");
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
    public List<Comment> getCommentsByEventId(Long eventId, int from, int size) {
        Pageable pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CompilationNotFounfExeption("event with id  not found"));
        return commentRepository.findAllByEventId(eventId, pageable);
    }

    @Override
    public CommentDto patchComment(CommentDto commentDto, Long userId, Long eventId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CompilationNotFounfExeption("user with id = " + userId + " not found"));

        Comment comment = commentRepository.findCommetnByserIdAnfEventId(userId, eventId);
        if (comment == null) throw new CompilationNotFounfExeption("comment with id = " + userId + " not found");
        if (!comment.getState().equals(WAITING)) {
            throw new WrongPatchException("Можно только изменять комментарии со статусом  WAITING");
        }
        if (commentDto.getDescription() == null) {
            throw new RuntimeException("нечего изменять, нужно указать описание");
        }


        comment.setDescription(commentDto.getDescription());
        commentRepository.save(comment);
        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public void deleteComment(Long userId, Long eventId) {
        Comment comment = commentRepository.findCommetnByserIdAnfEventId(userId, eventId);
        if (comment == null) {
            throw new RuntimeException("коментарий с таким номером не найден");
        }
        commentRepository.delete(comment);
    }

    @Override
    public Comment getComment(Long userId, Long eventId) {
        Comment comment = commentRepository.findCommetnByserIdAnfEventId(userId, eventId);
        if (comment == null) {
            throw new RuntimeException("коментарий с таким номером не найден");
        }
        return comment;
    }

    @Override
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findAllCommentsByUserId(userId);
    }
}
