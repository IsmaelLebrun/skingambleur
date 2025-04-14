package com.example.user.service;

import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.example.user.repository.client.UserMFeignClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMFeignClient userMFeignClient;

    public UserService(UserRepository userRepository, UserMFeignClient userMFeignClient) {
        this.userRepository = userRepository;
        this.userMFeignClient = userMFeignClient;
    }

    public String createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) == null){
            if(userRepository.findByPseudo(user.getPseudo()) == null){
                User newUser = userRepository.save(user);
                String result = userMFeignClient.createUser(newUser.getId());
                if(!result.equals("success")){
                    return "errorCreatingUserM";
                }
                return "success";
            }else{
                return "pseudoAlreadyTaken";
            }
        }else{
            return "mailAlreadyExist";
        }
    }
}
