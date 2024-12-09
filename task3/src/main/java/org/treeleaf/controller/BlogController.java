package org.treeleaf.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.treeleaf.dto.BlogDto;
import org.treeleaf.dto.BlogResponseDto;
import org.treeleaf.dto.CommentDto;
import org.treeleaf.dto.CommentResponseDto;
import org.treeleaf.entity.Blog;
import org.treeleaf.entity.Comment;
import org.treeleaf.entity.User;
import org.treeleaf.service.BlogService;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_BLOG')")
    public ResponseEntity<BlogResponseDto> createBlog(@Valid @RequestBody BlogDto dto,
                                                      Authentication auth) {
        User user = (User) auth.getPrincipal();
        Blog blog = blogService.createBlog(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToResponseDto(blog));
    }

    @GetMapping
    public ResponseEntity<List<BlogResponseDto>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        List<BlogResponseDto> response = blogs.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_BLOG')")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable Long id,
                                                      @Valid @RequestBody BlogDto dto, Authentication auth) {
        User user = (User) auth.getPrincipal();
        Blog blog = blogService.updateBlog(id, dto, user);
        return ResponseEntity.ok(convertToResponseDto(blog));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_BLOG')")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        blogService.deleteBlog(id, user);
        return ResponseEntity.noContent().build();
    }

    private BlogResponseDto convertToResponseDto(Blog blog) {
        BlogResponseDto dto = new BlogResponseDto();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setThumbnailUrl(blog.getThumbnailUrl());
        return dto;
    }

    @PostMapping("/{id}/comments")
    @PreAuthorize("hasAuthority('WRITE_COMMENT')")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable Long id,
                                                         @Valid @RequestBody CommentDto dto,
                                                         Authentication auth) {
        User user = (User) auth.getPrincipal();
        Comment comment = blogService.addComment(id, dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToCommentResponseDto(comment));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long id) {
        List<Comment> comments = blogService.getCommentsByBlog(id);
        List<CommentResponseDto> response = comments.stream()
                .map(this::convertToCommentResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private CommentResponseDto convertToCommentResponseDto(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setAuthor(comment.getAuthor().getUsername());
        return dto;
    }

}
