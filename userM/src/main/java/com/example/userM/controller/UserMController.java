package com.example.userM.controller;

import com.example.userM.service.UserMService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMController {

    private final UserMService userMService;

    public UserMController(UserMService userMService) {
        this.userMService = userMService;
    }

    @PostMapping("userM/createUserM")
    public String createUser(@RequestBody Integer userId){
        return userMService.createUser(userId);
    }
}
