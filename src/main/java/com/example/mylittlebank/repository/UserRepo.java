package com.example.mylittlebank.repository;

import com.example.mylittlebank.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findById(long id);
}
