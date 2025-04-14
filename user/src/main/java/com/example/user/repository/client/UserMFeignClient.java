package com.example.user.repository.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "userM", url = "http://localhost:8030/")
public interface UserMFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "userM/createUserM", consumes = "application/json")
    String createUser(@RequestBody Integer id);
}
