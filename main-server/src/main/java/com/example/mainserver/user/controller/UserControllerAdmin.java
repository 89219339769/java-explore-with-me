package com.example.mainserver.user.controller;

import com.example.mainserver.user.UserService;
import com.example.mainserver.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class UserControllerAdmin {

    private final UserService userService;



    @PostMapping
    public User create(@RequestBody User user,  HttpServletResponse response) {

        response.setStatus(201);
        return userService.saveUser(user);
    }




    @GetMapping()
    public List<User>  findUserById(@RequestParam(required = false) List<Long> ids,
                                    @RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "10") int size) {
        return userService.getUsers(ids, from, size);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id,  HttpServletResponse response) {
        response.setStatus(204);
        userService.delete(id);
    }
}
