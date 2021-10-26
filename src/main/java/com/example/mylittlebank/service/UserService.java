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

        User userFromDb=findUserById(id);

        String userFullName=userFromDb.getFullName();

        if(user.getFullName() != null && !user.getFullName().equals(userFullName)){
            userFromDb.setFullName(user.getFullName());
        }

        String userEmail=userFromDb.getEmail();

        if(user.getEmail() != null && !user.getEmail().equals(userEmail)){
            userFromDb.setEmail(user.getEmail());
        }

        String userPhone=userFromDb.getPhone();

        if(user.getPhone() != null && !user.getPhone().equals(userPhone)){
            userFromDb.setPhone((user.getPhone()));
        }

        String userAddress=userFromDb.getEmail();

        if (user.getAddress() !=null && !user.getAddress().equals(userAddress)){
            userFromDb.setAddress(user.getAddress());
        }



    }
}
