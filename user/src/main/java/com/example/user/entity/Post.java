package com.example.user.entity;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private Long id;
    private String title;
    private String text;
    private List<PostType> postTypes;
    private LocalDateTime createdAt;
}
