package com.example.mylittlebank.service;

import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.persistence.model.User;
import com.example.mylittlebank.persistence.repository.UserRepo;
import com.example.mylittlebank.service.mapper.IUserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    void addUser() {

        UserDto userDto = new UserDto();

        boolean isUserCreated = userService.addUser(userDto);

        Assertions.assertTrue(isUserCreated);
        Mockito.verify(userRepo).save(IUserMapper.USER_MAPPER.mapToUser(userDto));

    }
}