package com.example.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80, nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String text;
    private List<PostType> postTypes;
    private LocalDateTime createdAt;
    private Long authorId;
}
