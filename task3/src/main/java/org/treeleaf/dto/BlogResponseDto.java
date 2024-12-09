package org.treeleaf.dto;

import lombok.Data;

import java.util.List;

@Data
public class BlogResponseDto {
    private Long id;
    private String title;
    private String content;
    private String thumbnailUrl;
    private UserResponseDto author;
    private List<CommentResponseDto> comments;
}
