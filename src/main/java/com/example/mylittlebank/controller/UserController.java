package com.example.mylittlebank.controller;

import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.UserRepo;
import com.example.mylittlebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers(){

        return userService.findAllUsers();
    }

    @PostMapping
    public boolean addNewUser(@RequestBody User user){

         return  userService.addUser(user);

    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id){

        return userService.findUserById(id);

    }

    @PatchMapping("/{id}")
    public User changeUser(@PathVariable String id, @RequestBody User user){



        userService.updateProfile(id,user);
        return null;
    }
}
