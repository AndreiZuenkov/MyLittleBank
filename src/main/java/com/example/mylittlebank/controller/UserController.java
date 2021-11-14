package com.example.mylittlebank.controller;

import com.example.mylittlebank.controller.dto.Response;
import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.exception.UserException;
import com.example.mylittlebank.persistence.model.User;
import com.example.mylittlebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public List<User> findAllUsers() {
//
//        return userService.findAllUsers();
//    }

    @GetMapping
    public User findUser(@RequestParam(required = false, defaultValue = "") String phone,
                         @RequestParam(required = false, defaultValue = "") String fullName,
                         @RequestParam(required = false, defaultValue = "") String email) {

        return userService.findUser(phone, fullName, email);
    }


    @PostMapping
    public Response addUser(@RequestBody UserDto userDto) throws UserException {

        if (!userService.addUser(userDto)) {
            throw new UserException("User not add");
        }

        return new Response("Ok");
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
    public boolean deleteUser(@PathVariable String id) {

        return userService.deleteUser(id);
    }
}
