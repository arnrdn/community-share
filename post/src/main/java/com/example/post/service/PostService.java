package com.example.post.service;

import com.example.post.dto.PostRequest;
import com.example.post.dto.PostResponse;
import com.example.post.entity.Comment;
import com.example.post.entity.Post;
import com.example.post.exception.PostNotFoundException;
import com.example.post.feign.CommentClient;
import com.example.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentClient commentClient;

    public void savePost(PostRequest postRequest) {
        Post post = Post.builder()
                .authorId(postRequest.getAuthorId())
                .title(postRequest.getTitle())
                .text(postRequest.getText())
                .createdAt(LocalDateTime.now())
                .postTypes(postRequest.getPostTypes())
                .build();

        postRepository.save(post);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public List<Post> findAllPostsByUser(Long id) {
        return postRepository.findAllByAuthorId(id);
    }

    @Transactional
    public PostResponse findPostWithComments(Long id) throws PostNotFoundException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(String.format("Post with id: %s was not found", id)));

        List<Comment> commentList = commentClient.findAllCommentsByPost(id);
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .text(post.getText())
                .postTypes(post.getPostTypes())
                .createdAt(post.getCreatedAt())
                .authorId(post.getAuthorId())
                .commentList(commentList)
                .build();

    }
}
