package com.example.mylittlebank.service.mapper;

import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.persistence.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {IUserMapperImpl.class})
class IUserMapperTest {

    @Autowired
    private IUserMapperImpl userMapper;


    @Test
    void mapToUserDto() {

        User user=new User(){{
            setAddress("address");
            setEmail("email");
            setDateOfBirth(LocalDate.now());
            setFullName("Name");
            setPhone("123456789");
        }};

        UserDto userDto=userMapper.mapToUserDto(user);

        Assertions.assertEquals(user.getAddress(), userDto.getAddress());
        Assertions.assertEquals(user.getEmail(), userDto.getEmail());
        Assertions.assertEquals(user.getFullName(), userDto.getFullName());
        Assertions.assertEquals(user.getPhone(), userDto.getPhone());
        Assertions.assertEquals(user.getDateOfBirth(), userDto.getDateOfBirth());


    }

    @Test
    void mapToUser() {

        UserDto userDto=new UserDto(){{
            setAddress("address");
            setEmail("email");
            setDateOfBirth(LocalDate.now());
            setFullName("Name");
            setPhone("123456789");
        }};

        User user=userMapper.mapToUser(userDto);

        Assertions.assertEquals(userDto.getAddress(), user.getAddress());
        Assertions.assertEquals(userDto.getEmail(), user.getEmail());
        Assertions.assertEquals(userDto.getFullName(), user.getFullName());
        Assertions.assertEquals(userDto.getPhone(), user.getPhone());
        Assertions.assertEquals(userDto.getDateOfBirth(), user.getDateOfBirth());


    }
}