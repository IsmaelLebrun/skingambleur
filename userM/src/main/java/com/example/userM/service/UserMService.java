package com.example.userM.service;

import com.example.user.model.UserM;
import com.example.userM.repository.UserMRepository;
import org.springframework.stereotype.Service;

@Service
public class UserMService {
    private final UserMRepository userMRepository;

    public UserMService(UserMRepository userMRepository) {
        this.userMRepository = userMRepository;
    }

    public String createUser(Integer userId) {
        userMRepository.save(new UserM(0L, null, userId));
        return "success";
    }
}
