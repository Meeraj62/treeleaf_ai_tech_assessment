package org.treeleaf.dto;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String content;
    private String author;
}
