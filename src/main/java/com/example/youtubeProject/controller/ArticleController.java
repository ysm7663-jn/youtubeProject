package com.example.youtubeProject.controller;

import com.example.youtubeProject.dto.ArticleForm;
import com.example.youtubeProject.entity.Article;
import com.example.youtubeProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j  // 로깅을 위한 @
public class ArticleController {

    @Autowired  // 스프링 부트가 미리 생성해놓은 객체를 가져다 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        // System.out.println(form.toString());
        // 실제 프로그램에선 성능을 저하시키니 '로깅' 으로 대체
        // 로깅 : 블랙박스 처럼 기록을 남김

        log.info(form.toString());

        // 1. Dto를 Entity로 변환
        Article article = form.toEntity();
        // System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB에 저장하게 함
        Article save = articleRepository.save(article);
        // System.out.println(save.toString());
        log.info(save.toString());

        return "redirect:/articles/" + save.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴
        // Optional <Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지를 설
      return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 1. 모든 article을 가져온다
        // 방법 3가지
        // 1. 캐스팅 : List<Article> articleEntityList = (List<Article>) articleRepository.findAll();
        // 2. findAll() 메소드 형식에 맞춰 Iterable : Iterable articleEntityList = articleRepository.findAll();
        // 3. 오버라이딩(Repository)
        List<Article> articleEntityList = articleRepository.findAll(); // 오버라이딩

        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지를 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {

        log.info(form.toString());

        // 1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. Entity를 DB로 저장
        // 2-1. DB에서 기존 데이터를 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터에 값을 갱신한다
        if (target != null) {
            articleRepository.save(articleEntity);  // Entity가 DB로 갱신된다.
        }

        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }
}