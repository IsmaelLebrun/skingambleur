package com.example.user.controller;


import com.example.user.model.User;
import com.example.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/createUser")
    public String createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("user/findByEmail")
    public User findByEmail(@RequestBody String email){
        return userService.findByEmail(email).get();
    }
}
