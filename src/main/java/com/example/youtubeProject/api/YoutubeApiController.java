package com.example.youtubeProject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI용 컨트롤러 >> JSON 반환
public class YoutubeApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }

    /*
        일반 Controller 와 RestAPI Controller의 차이

        일반 : html 형태 반환
        RestApi : JSON, 텍스트 반환(데이터 반환)
     */


}
