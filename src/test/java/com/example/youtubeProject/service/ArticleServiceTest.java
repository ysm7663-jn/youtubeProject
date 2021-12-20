package com.example.youtubeProject.service;

import com.example.youtubeProject.dto.ArticleDto;
import com.example.youtubeProject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void index() {

        // 예상
        Article a = new Article(1L, "가가가가", "1111", "ysm");
        Article b = new Article(2L, "나나나나", "2222", "abc");
        Article c = new Article(3L, "다다다다", "3333", "cka");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111", "ysm");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_성공_존재하지않는_id_입력() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content_와author가_있는_dto() {

        // 예상
        String title = "라라라라";
        String content = "4444";
        String author = "daniel";
        ArticleDto dto = new ArticleDto(null, title, content, author);
        Article expected = new Article(4L, title, content, author);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

   @Test
   @Transactional
    void create_실패_id가포함된_dto가_입력() {

       // 예상
       String title = "라라라라";
       String content = "4444";
       String author = "daniel";
       ArticleDto dto = new ArticleDto(4L, title, content, author);
       Article expected = null;

       // 실제
       Article article = articleService.create(dto);

       // 비교
       assertEquals(expected, article);
    }
}