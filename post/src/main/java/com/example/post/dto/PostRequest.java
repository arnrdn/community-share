package com.example.post.dto;


import com.example.post.entity.PostType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long authorId;
    private String title;
    private String text;
    private List<PostType> postTypes;
}
