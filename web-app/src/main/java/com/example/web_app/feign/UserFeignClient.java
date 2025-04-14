package com.example.web_app.feign;

import com.example.web_app.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "user", url = "http://localhost:8020/")
public interface UserFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "user/createUser", consumes = "application/json")
    String createUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.POST, value = "user/findByEmail", consumes = "application/json")
    Optional<User> findByEmail(@RequestBody String email);
}
