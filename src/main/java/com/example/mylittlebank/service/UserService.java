package com.example.mylittlebank.service;

import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> findAllUsers() {

        List<User> userList = userRepo.findAll();

        return userList;
    }

    public boolean addUser(User user) {

        User userFromDb = userRepo.findByFullName(user.getFullName());

        if (userFromDb != null) {
            return false;
        }
        userRepo.save(user);

        return true;
    }

    public User findUserById(String idFromQuery){

        long id=Long.parseLong(idFromQuery);
        User userFromDb=userRepo.findById(id);

        if(userFromDb != null )
            return userFromDb;

        return null;

    }

    public void updateProfile(String id, User user) {

        User userFromDb = userRepo.findById(Long.parseLong(id));


    }
}
