package com.example.mylittlebank.service;

import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.persistence.model.User;
import com.example.mylittlebank.persistence.repository.UserRepo;
import com.example.mylittlebank.service.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> findAllUsers() {

        List<User> userList = userRepo.findAll();

        return userList;
    }


    public boolean addUser(UserDto userDto) {

        if (userDto != null) {

            User userFromDb = userRepo.findByFullName(IUserMapper.USER_MAPPER.mapToUser(userDto).getFullName());

            if (userFromDb != null) {
                return false;
            }
            userRepo.save(IUserMapper.USER_MAPPER.mapToUser(userDto));

            return true;
        }
        return false;
    }

    public User findUserById(String idFromQuery) {

        return userRepo.findById(Integer.parseInt(idFromQuery));

    }


    public void updateProfile(String id, UserDto userDto) {

        User userFromDb = findUserById(id);

        checkUserData(IUserMapper.USER_MAPPER.mapToUser(userDto), userFromDb);

        userRepo.save(userFromDb);

    }

    private void checkUserData(User userFromQuery, User userFromDb) {

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

    public User findUser(String phoneFromQuery, String fullNameFromQuery, String emailFromQuery) {

        if (phoneFromQuery != null && !phoneFromQuery.isEmpty()) {
            return userRepo.findByPhone(phoneFromQuery);
        }

        if (fullNameFromQuery != null && !fullNameFromQuery.isEmpty()) {
            return userRepo.findByFullName(fullNameFromQuery);
        }

        if (emailFromQuery != null && !emailFromQuery.isEmpty()) {
            return userRepo.findByEmail(emailFromQuery);
        }

        return null;
    }


}
