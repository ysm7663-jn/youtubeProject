package com.example.youtubeProject.dto;

import com.example.youtubeProject.entity.User;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserDto {

    private Long userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userAge;
    private String userTel;

    public User toEntity() {
        return new User(userNo, userId, userPw, userName, userAge, userTel);
    }
}
