package com.example.mainserver.user;

import com.example.mainserver.exceptions.UserBadNameException;
import com.example.mainserver.exceptions.UserNotFoundException;
import com.example.mainserver.exceptions.WrongPatchException;
import com.example.mainserver.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User saveUser(User user) {
        if (user.getName() == null) {
            throw new UserBadNameException("имя не должно быть пустым");
        }

        User suerName = repository.getUserByName(user.getName());
        if (suerName != null) {

            throw new WrongPatchException("уже существует юзер с таким именем");
        }

        repository.save(user);
        return user;
    }


    @Override
    public void delete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id = " + id + " not found"));
        repository.deleteById(id);

    }

    @Override
    public List<User> getUsers(List<Long> ids, int from, int size) {
        Pageable pageable = PageRequest.of(from, size, Sort.by(Sort.Direction.ASC, "id"));
        return repository.findAllByIdIn(ids, pageable);

    }
}
