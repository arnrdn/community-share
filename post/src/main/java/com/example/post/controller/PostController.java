package com.example.post.controller;

import com.example.post.dto.PostRequest;
import com.example.post.dto.PostResponse;
import com.example.post.entity.Post;
import com.example.post.exception.PostNotFoundException;
import com.example.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(@RequestBody PostRequest req) {
        postService.savePost(req);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Post>> findAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Post>> findAllPostsByUser(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(postService.findAllPostsByUser(id));
    }

    @GetMapping("/comments/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostResponse> findPostWithComments(@PathVariable("postId") Long id) throws PostNotFoundException {
        return ResponseEntity.ok(postService.findPostWithComments(id));
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlePostNotFoundException(PostNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
