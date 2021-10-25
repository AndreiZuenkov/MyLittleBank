package com.example.mylittlebank.controller;

import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public Iterable<User> findAllUsers(){

        Iterable<User> users=userRepo.findAll();

        return users;
    }

    @PostMapping
    public boolean addNewUser(@RequestBody User user){

        userRepo.save(user);

         return  true;

    }
}
