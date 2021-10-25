package com.example.mylittlebank.controller;

import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.UserRepo;
import com.example.mylittlebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> findAllUsers(){

        Iterable<User> users=userRepo.findAll();

        return users;
    }

    @PostMapping
    public boolean addNewUser(@RequestBody User user){

        userRepo.save(user);

         return  userRepo.existsById(user.getId());

    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id){

        long longId=Long.parseLong(id);
        User user=userRepo.findById(longId);

        return user;
    }

    @PatchMapping("/{id}")
    public User changeUser(@PathVariable String id, @RequestBody User user){



        userService.updateProfile(id,user);
        return null;
    }
}
