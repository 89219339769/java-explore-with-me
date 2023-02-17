package com.example.mainserver.user.controller;

import com.example.mainserver.user.UserService;
import com.example.mainserver.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;



    @PostMapping
    public User create(@RequestBody User user) {
        return userService.saveUser(user);
    }




//    @GetMapping("/{id}")
//    public User findUserById(@PathVariable Long id) {
//        return userService.get(id);
//
//    }

//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//    }
}
