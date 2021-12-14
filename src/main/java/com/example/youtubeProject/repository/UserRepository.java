package com.example.youtubeProject.repository;

import com.example.youtubeProject.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRespository extends CrudRepository<User, Long> {
}
