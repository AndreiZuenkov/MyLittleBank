package com.example.mylittlebank.service;

import com.example.mylittlebank.dto.UserDto;
import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.UserRepo;
import com.example.mylittlebank.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUsers() {

        List<User> userList = userRepo.findAll();

        return userList;
    }

//    public boolean addUser(User user) {
//
//        User userFromDb = userRepo.findByFullName(user.getFullName());
//
//        if (userFromDb != null) {
//            return false;
//        }
//        userRepo.save(user);
//
//        return true;
//    }
    public boolean addUser(UserDto userDto) {

        User userFromDb = userRepo.findByFullName(userMapper.mapToUser(userDto).getFullName());

        if (userFromDb != null) {
            return false;
        }
        userRepo.save(userMapper.mapToUser(userDto));

        return true;
    }

    public User findUserById(String idFromQuery) {

        long id = Long.parseLong(idFromQuery);

        User userFromDb = userRepo.findById(id);

        return userFromDb;

    }

    public void updateProfile(String id, User user) {

        User userFromDb = findUserById(id);

        checkUserData(user,userFromDb);

        userRepo.save(userFromDb);

    }

    private void checkUserData(User userFromQuery, User userFromDb){

        String userFullName = userFromDb.getFullName();

        if (userFromQuery.getFullName() != null && !userFromQuery.getFullName().equals(userFullName)) {
            userFromDb.setFullName(userFromQuery.getFullName());
        }

        String userEmail = userFromDb.getEmail();

        if (userFromQuery.getEmail() != null && !userFromQuery.getEmail().equals(userEmail)) {
            userFromDb.setEmail(userFromQuery.getEmail());
        }

        String userPhone = userFromDb.getPhone();

        if (userFromQuery.getPhone() != null && !userFromQuery.getPhone().equals(userPhone)) {
            userFromDb.setPhone((userFromQuery.getPhone()));
        }

        String userAddress = userFromDb.getEmail();

        if (userFromQuery.getAddress() != null && !userFromQuery.getAddress().equals(userAddress)) {
            userFromDb.setAddress(userFromQuery.getAddress());
        }

        LocalDate userDateOfBirth = userFromDb.getDateOfBirth();

        if (userFromQuery.getDateOfBirth() != null && userFromQuery.getDateOfBirth().equals(userDateOfBirth)) {
            userFromDb.setDateOfBirth(userFromQuery.getDateOfBirth());

        }
    }

    public boolean deleteUser(String id) {

        if (findUserById(id) != null) {
            userRepo.delete(findUserById(id));
            return true;
        }

        return false;
    }
}
