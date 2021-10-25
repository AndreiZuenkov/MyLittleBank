package com.example.mylittlebank.service;

import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void updateProfile(String id, User user){
        User userFromDb=userRepo.findById(Long.parseLong(id));


    }
}
