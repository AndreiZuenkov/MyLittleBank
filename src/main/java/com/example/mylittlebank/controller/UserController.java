package com.example.mylittlebank.controller;

import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.model.User;
import com.example.mylittlebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers() {

        return userService.findAllUsers();
    }


    @PostMapping
    public boolean addUser(@RequestBody UserDto userDto){

        return userService.addUser(userDto);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {

        return userService.findUserById(id);

    }

    @PatchMapping("/{id}")
    public User updateProfile(@PathVariable String id, @RequestBody UserDto userDto) {

        userService.updateProfile(id, userDto);

        return userService.findUserById(id);
    }

    @DeleteMapping("{id}")
    public boolean deleteUser(@PathVariable String id){

        return userService.deleteUser(id);
    }
}
