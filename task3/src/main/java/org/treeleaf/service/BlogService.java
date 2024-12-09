package org.treeleaf.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.treeleaf.dto.BlogDto;
import org.treeleaf.dto.CommentDto;
import org.treeleaf.entity.Blog;
import org.treeleaf.entity.Comment;
import org.treeleaf.entity.User;
import org.treeleaf.repository.BlogRepository;
import org.treeleaf.repository.CommentRepository;

import java.util.List;

@Service
@Transactional
public class BlogService {
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public BlogService(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    public Blog createBlog(BlogDto dto, User author) {
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setThumbnailUrl(dto.getThumbnailUrl());
        blog.setAuthor(author);

        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog updateBlog(Long id, BlogDto dto) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found"));

        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setThumbnailUrl(dto.getThumbnailUrl());

        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public Comment addComment(Long blogId, CommentDto dto, User author) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("Blog not found"));

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setBlog(blog);
        comment.setAuthor(author);

        return commentRepository.save(comment);
    }
}
