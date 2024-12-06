package org.treeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.treeleaf.entity.Blog;
import org.treeleaf.entity.User;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByAuthor(User author);
}
