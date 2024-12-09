package org.treeleaf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.treeleaf.dto.BlogDto;
import org.treeleaf.dto.CommentDto;
import org.treeleaf.entity.Blog;
import org.treeleaf.entity.Comment;
import org.treeleaf.entity.User;
import org.treeleaf.service.BlogService;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDto dto, Principal principal) {
        User author = (User) ((Authentication) principal).getPrincipal();
        Blog blog = blogService.createBlog(dto, author);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody BlogDto dto) {
        Blog updatedBlog = blogService.updateBlog(id, dto);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody CommentDto dto, Principal principal) {
        User author = (User) ((Authentication) principal).getPrincipal();
        Comment comment = blogService.addComment(id, dto, author);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
}
