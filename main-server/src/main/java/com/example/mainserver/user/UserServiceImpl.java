package com.example.mainserver.user;

import com.example.mainserver.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User saveUser(User user) {
        repository.save(user);
        return user;
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }
}
