package com.example.mylittlebank.controller;

import com.example.mylittlebank.controller.dto.Response;
import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.exception.UserException;
import com.example.mylittlebank.persistence.model.User;
import com.example.mylittlebank.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(description = "Поиск пользователей по номеру телефона, имени, email")
    public User findUser(@RequestParam(required = false, defaultValue = "") String phone,
                         @RequestParam(required = false, defaultValue = "") String fullName,
                         @RequestParam(required = false, defaultValue = "") String email) {

        return userService.findUser(phone, fullName, email);
    }


    @PostMapping
    @Operation(description = "Добавление нового пользователя")
    public Response addUser(@Valid @RequestBody UserDto userDto) throws UserException {


        if (!userService.addUser(userDto)) {
            throw new UserException("User not add");
        }

        return new Response("Ok");
    }

    @GetMapping("/{id}")
    @Operation(description = "Поиск пользователя по id")
    public User findUserById(@PathVariable String id) {

        return userService.findUserById(id);

    }

    @PatchMapping("/{id}")
    @Operation(description = "Обновление данных пользователя")
    public User updateProfile(@PathVariable String id, @Valid @RequestBody UserDto userDto) {

        userService.updateProfile(id, userDto);

        return userService.findUserById(id);
    }

    @DeleteMapping("{id}")
    @Operation(description = "Удаление пользователя")
    public boolean deleteUser(@PathVariable String id) {

        return userService.deleteUser(id);
    }
}
