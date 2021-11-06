package com.example.mylittlebank.controller;

import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.model.User;
import com.example.mylittlebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                         @RequestParam(required = false, defaultValue = "") String email){

        return userService.findUser(phone,fullName,email);
    }


    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDto){ //FIXME не возвращает http status

        if(userService.addUser(userDto)){
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.noContent().build();
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
