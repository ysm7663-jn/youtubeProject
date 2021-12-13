package com.example.youtubeProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/users/login")
    public String index() {
        return "/users/login";
    }



}
