package com.example.mainserver.user;


import com.example.mainserver.user.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    void delete(Long id);

    List<User> getUsers(List<Long> ids, int from, int size);
}
