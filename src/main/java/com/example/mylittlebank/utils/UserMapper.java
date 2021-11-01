package com.example.mylittlebank.utils;

import com.example.mylittlebank.dto.UserDto;

import com.example.mylittlebank.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto mapToUserDto(User user){

        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setDateOfBirth(user.getDateOfBirth());

        return userDto;
    }

    public User mapToUser(UserDto userDto){

        User user=new User();
        user.setId(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setDateOfBirth(userDto.getDateOfBirth());

        return user;
    }

}
