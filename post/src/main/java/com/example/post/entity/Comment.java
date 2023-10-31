package com.example.post.entity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    private Long id;
    private Long postId;
    private String authorUsername;
    private Long authorId;
    private String text;
    private LocalDateTime createdAt;
}
