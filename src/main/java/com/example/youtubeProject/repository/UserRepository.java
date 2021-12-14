package com.example.youtubeProject.repository;

import com.example.youtubeProject.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
