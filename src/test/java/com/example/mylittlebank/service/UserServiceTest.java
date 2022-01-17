package com.example.mylittlebank.service;

import com.example.mylittlebank.controller.dto.UserDto;
import com.example.mylittlebank.persistence.model.User;
import com.example.mylittlebank.persistence.repository.UserRepo;
import com.example.mylittlebank.service.mapper.IUserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    void testAddUser() {

        UserDto userDto = new UserDto();

        boolean isUserCreated = userService.addUser(userDto);
        boolean isUserNotCreated = userService.addUser(null);


        Assertions.assertTrue(isUserCreated);
        Assertions.assertFalse(isUserNotCreated);
        Mockito.verify(userRepo).save(IUserMapper.USER_MAPPER.mapToUser(userDto));

    }

    @Test
    public void testFindUserById() {

        String idFromQuery = "1";

        userService.findUserById(idFromQuery);

        Mockito.verify(userRepo).findById(Long.parseLong(idFromQuery));


    }

    @Test
    public void testUpdateProfile() {

        UserDto userDto = new UserDto();
        User user = new User();
        String idFromQuery = "1";

        Mockito.when(userRepo.findById(Long.parseLong(idFromQuery))).thenReturn(user);

        userService.updateProfile(idFromQuery, userDto);


        Mockito.verify(userRepo).save(Mockito.any());

    }

    @Test
    public void testDeleteUser() {

        String id = "1";
        String idIsNotExist = "2";

        Mockito.when(userRepo.findById(Long.parseLong(idIsNotExist))).thenReturn(null);
        Mockito.when(userRepo.findById(Long.parseLong(id))).thenReturn(new User());


        boolean isUserDelete = userService.deleteUser(id);
        boolean isUserDoNotDelete = userService.deleteUser(idIsNotExist);

        Assertions.assertTrue(isUserDelete);
        Assertions.assertFalse(isUserDoNotDelete);

        Mockito.verify(userRepo).delete(Mockito.any());
    }

    @Test
    public void testFindUser() {

        String phoneFromQuery = " ";
        String fullNameFromQuery = " ";
        String emailFromQuery = " ";

        userService.findUser(phoneFromQuery, fullNameFromQuery, emailFromQuery);
        userService.findUser(null, fullNameFromQuery, emailFromQuery);
        userService.findUser(null, null, emailFromQuery);

        User userNotFound=userService.findUser(null,null,null);


        Mockito.verify(userRepo).findByPhone(phoneFromQuery);
        Mockito.verify(userRepo).findByFullName(fullNameFromQuery);
        Mockito.verify(userRepo).findByEmail(emailFromQuery);

        Assertions.assertEquals(null,userNotFound);

    }
}

