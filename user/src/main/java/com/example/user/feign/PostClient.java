package com.example.user.feign;

import com.example.user.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "post-service", url = "${application.config.posts-url}")
public interface PostClient {

    @GetMapping("/{userId}")
    List<Post> findAllPostsByUser(@PathVariable("userId") Long id);

}
