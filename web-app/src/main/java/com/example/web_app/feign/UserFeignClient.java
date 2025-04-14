package com.example.web_app.feign;

import com.example.web_app.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user", url = "http://localhost:8020/")
public interface UserFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "user/createUser", consumes = "application/json")
    String createUser(@RequestBody User user);
}
