package com.example.mylittlebank.repository;

import com.example.mylittlebank.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findById(long id);

    User findByFullName(String fullName);

    List<User> findAll();

    User findByPhone(String phone);

    User findByEmail(String email);
}
