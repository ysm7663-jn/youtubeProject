package com.example.youtubeProject.controller;

import com.example.youtubeProject.dto.UserDto;
import com.example.youtubeProject.entity.User;
import com.example.youtubeProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/login")
    public String loginPage() {
        return "users/login";
    }

    @GetMapping("/users/new")
    public String createPage() {
        return "users/new";
    }

    @PostMapping("/users/createId")
    public String createId(UserDto dto) {

        log.info(dto.toString());

        // 1. Dto >> Entity
        User user = dto.toEntity();

        log.info(user.toString());

        // 2. Entity >> Repository
        User save = userRepository.save(user);
        log.info(save.toString());

        return "redirect:/users/login";
    }

}
