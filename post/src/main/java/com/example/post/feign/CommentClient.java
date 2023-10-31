package com.example.post.feign;

import com.example.post.entity.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "comment-service", url = "${application.config.comments-url}")
public interface CommentClient {

    @GetMapping("/{postId}")
    List<Comment> findAllCommentsByPost(@PathVariable("postId") Long id);
}
