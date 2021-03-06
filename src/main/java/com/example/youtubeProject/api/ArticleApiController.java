package com.example.youtubeProject.api;

import com.example.youtubeProject.dto.ArticleDto;
import com.example.youtubeProject.entity.Article;
import com.example.youtubeProject.repository.ArticleRepository;
import com.example.youtubeProject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // RestAPI 용 컨트롤러 >> 데이터(JSON)을 반환
@Slf4j
public class ArticleApiController {

    /*
        Status code :
        Successful : 200(ok) / 201(create)
        Client Error : 404(Not Found)
        Server Error : 500(Internet Server Error)

        RestAPI란?
        : 웹 서버의 자원을 client에 구애받지 않고 사용할 수 있게 해주는 설계 방식, http를 통해 서버의 자원을 다루게 하는 방식
        : 모든 기기에서 통용될 수 있는 화면이 아닌 데이터 만을 반환
        : 과거 xml이었지만 최근 JSON으로 사용

        JSON??
        : JavaScript 방식을 차용한 객체 표현식

     */

    @Autowired  // DI, 생성 객체를 가져와 연결
    private ArticleService articleService;

    // GET
    // 1-1. 전체 GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    // 1-2. 단일 GET
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // 2. POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleDto dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 3. PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update (@PathVariable Long id,
                                           @RequestBody ArticleDto dto) {

        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 4. Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    // 트랜잭션 => 실패 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleDto> dtos) {

        List<Article> createList = articleService.createArticles(dtos);

        return (createList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /*
    @Autowired // DI
    private ArticleRepository articleRepository;

    // 1-1. GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    // 1-2. 단일 GET
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // 2. POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleDto dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // 3. PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update (@PathVariable Long id,
                                     @RequestBody ArticleDto dto) {

        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();

        log.info("id : {}, article : {}", id, article.toString());

        // 2. 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
        }

        // 4. 업데이트 및 정상 응답(200)
        target.patch(article); // 업데이트에 값이 변경이 없거나 공백인 경우 기존 값을 그대로 가져감
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // 4. Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {

        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
        }

        // 3. 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
     */
}
