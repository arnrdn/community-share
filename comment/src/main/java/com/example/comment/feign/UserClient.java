package com.example.comment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping("/username/{userId}")
    String findUsernameByUserId(@PathVariable("userId") Long id);
}
