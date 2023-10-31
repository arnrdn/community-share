package com.example.comment.controller;

import com.example.comment.dto.CommentRequest;
import com.example.comment.entity.Comment;
import com.example.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveComment(@RequestBody CommentRequest req) {
        service.saveComment(req);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Comment>> findAllComments() {
        return ResponseEntity.ok(service.findAllComments());
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Comment>> findAllCommentsByPost(@PathVariable("postId") Long id) {
        return ResponseEntity.ok(service.findAllCommentsByPost(id));
    }
}
