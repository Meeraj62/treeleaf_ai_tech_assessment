package org.treeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.treeleaf.entity.Blog;
import org.treeleaf.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlog(Blog blog);
}
