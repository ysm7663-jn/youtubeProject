package com.example.youtubeProject.service;

import com.example.youtubeProject.dto.ArticleDto;
import com.example.youtubeProject.entity.Article;
import com.example.youtubeProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service    // 서비스 선언(서비스 객체를 스프링부트에 선언)
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleDto dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleDto dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();

        log.info("id : {}, article : {}", id, article.toString());

        // 2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return null;
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article); // 업데이트에 값이 변경이 없거나 공백인 경우 기존 값을 그대로 가져감
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리
        if (target == null) {
            return null;
        }

        // 3. 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional  // 해당 메소드를 트랜젝션으로 묶는다
    public List<Article> createArticles(List<ArticleDto> dtos) {

        // 1. dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        /*
            List<Article> articleList = new ArrayList<>();
            for (int i = 0; i < dtos.size(); i++) {
                ArticleDto dto = dtos.get(i);
                Article entity = dto.toEntity();
                articleList.add(entity);
             }
         */

        // 2. Entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        /*
            for (int i = 0; i < articleList.size(); i++) {
                Article article = articleList.get(i);
                articleRepository.save(article);
         */

        // 3. 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );

        // 4. 결과값 반환
        return articleList;
    }
}