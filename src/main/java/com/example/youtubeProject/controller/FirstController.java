package com.example.youtubeProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")  // 연결
    public String greetings(Model model) {
        model.addAttribute("username", "ysm");
        return "greetings"; // templates/greetings.mustache -> 브라우저로 전송
    }

    @GetMapping("/bye")
    public String seeYa(Model model) {
        model.addAttribute("nickname", "daniel");
        return "seeYa";
    }

}
