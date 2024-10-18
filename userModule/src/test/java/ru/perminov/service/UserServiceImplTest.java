package ru.perminov.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.perminov.dto.UserDtoIn;
import ru.perminov.dto.UserDtoOut;
import ru.perminov.model.User;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;



    @Test
    void getAll() {
        userService.create(UserDtoIn.builder()
                .username("test1")
                .password("test1")
                .email("a@aa1.ru")
                .build());
        userService.create(UserDtoIn.builder()
                .username("test2")
                .password("test2")
                .email("a@aa2.ru")
                .build());
        Assertions.assertEquals(userService.getAll().size(), 2);

    }

    @Test
    void getById() {
        UserDtoIn user = UserDtoIn.builder()
                .username("testGetById1")
                .password("test1")
                .email("a@aa1.ru")
                .build();
        Long l = userService.create(user).getId();
        Assertions.assertEquals(userService.getById(l).getUsername(), user.getUsername(), "Имя не соответствует созданному");
        Assertions.assertEquals(us);
    }

    @Test
    void create() {

    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getUserRole() {
    }

    @Test
    void addRoleByUser() {
    }

    @Test
    void deleteRoleByUser() {
    }
}