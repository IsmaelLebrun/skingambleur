package com.example.web_app.service;

import com.example.web_app.feign.UserFeignClient;
import com.example.web_app.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserFeignClient userFeignClient;

    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public String createUser(User user) {
        return userFeignClient.createUser(user);
    }
}
