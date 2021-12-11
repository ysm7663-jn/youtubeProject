package com.example.youtubeProject.repository;

import com.example.youtubeProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    // <관리대상 엔티티, 대표 값>

    @Override
    ArrayList<Article> findAll();
}
