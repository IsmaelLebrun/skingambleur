package com.example.web_app.service;

import com.example.web_app.feign.UserFeignClient;

import com.example.web_app.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class UsersDetailsService implements UserDetailsService {
    private final UserFeignClient userFeignClient;

    public UsersDetailsService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userFeignClient.findByEmail(email);
        if(user.isPresent()){
            return new org.springframework.security.core.userdetails.User(email, user.get().getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException(email);
    }
}
