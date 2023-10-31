package com.example.comment.service;

import com.example.comment.dto.CommentRequest;
import com.example.comment.entity.Comment;
import com.example.comment.feign.UserClient;
import com.example.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final UserClient userClient;

    public void saveComment(CommentRequest req) {
        String username = userClient.findUsernameByUserId(req.getAuthorId());
        Comment post = Comment.builder()
                .postId(req.getPostId())
                .authorId(req.getAuthorId())
                .authorUsername(username)
                .text(req.getText())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(post);
    }

    public List<Comment> findAllComments() {
        return repository.findAll();
    }

    @Transactional
    public List<Comment> findAllCommentsByPost(Long id) {
        return repository.findAllByPostId(id);
    }
}
