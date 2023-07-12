package com.CNUSWAcademy.MyBlog.repository;

import com.CNUSWAcademy.MyBlog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
