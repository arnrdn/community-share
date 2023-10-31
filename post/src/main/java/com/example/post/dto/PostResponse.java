package com.example.post.dto;

import com.example.post.entity.Comment;
import com.example.post.entity.PostType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String text;
    private List<PostType> postTypes;
    private LocalDateTime createdAt;
    private Long authorId;
    private List<Comment> commentList;
}
