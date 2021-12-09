package com.example.youtubeProject.controller;

import com.example.youtubeProject.dto.ArticleForm;
import com.example.youtubeProject.entity.Article;
import com.example.youtubeProject.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());

        // 1. Dto를 Entity로 변환
        Article article = form.toEntity();

        // 2. Repository에게 Entity를 DB에 저장하게 함
        Article save = articleRepository.save(article);
        return "";
    }
}
