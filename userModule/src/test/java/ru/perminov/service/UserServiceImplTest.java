package ru.perminov.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.perminov.dto.UserDtoIn;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.deleteAll();
    }

    @Test
    void getAllOK() {
        int l = userService.getAll().size();
        userService.create(UserDtoIn.builder()
                .username("name1")
                .password("pas1")
                .email("a@aa1.ru")
                .build());
        userService.create(UserDtoIn.builder()
                .username("name2")
                .password("pas2")
                .email("a@aa2.ru")
                .build());
        Assertions.assertEquals(userService.getAll().size(), l + 2);

    }

    @Test
    void getByIdOK() {
        UserDtoIn user = UserDtoIn.builder()
                .username("name")
                .password("pas")
                .email("a@aa.ru")
                .build();
        Long l = userService.create(user).getId();
        Assertions.assertEquals(userService.getById(l).getUsername(), user.getUsername(), "Имя не соответствует созданному");
    }

    void getByIdFail() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.getById(Long.valueOf(userService.getAll().size() + 1));
        });
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.getById(-1L);
        });
    }

    @Test
    void createOK() {
        UserDtoIn userDtoIn = UserDtoIn.builder()
                .username("test")
                .email("a@aa.ru")
                .password("123")
                .build();
        Long l = userService.create(userDtoIn).getId();
        Assertions.assertEquals(userService.getById(l).getUsername(), userDtoIn.getUsername());

    }

    @Test
    void createFill() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.create(UserDtoIn.builder().build());
        }, "");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userService.create(UserDtoIn.builder()
                    .email("a@aa.ru")
                    .password("123")
                    .build());
        }, "");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            userService.create(UserDtoIn.builder()
                    .username("test")
                    .password("123")
                    .build());
        }, "");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.create(UserDtoIn.builder()
                    .username("test")
                    .email("a@ru123.ru")
                    .build());
        }, "");
    }

    @Test
    void updateOK() {
        UserDtoIn userDtoIn = UserDtoIn.builder()
                .username("test")
                .email("a@aa.ru")
                .password("123")
                .build();
        Long l = userService.create(userDtoIn).getId();
        userService.update(UserDtoIn.builder()
                .username("testUpdate2")
                .build(), l);

        Assertions.assertEquals(userService.getById(l).getUsername(), "testUpdate2");
        userService.update(UserDtoIn.builder()
                .email("testemail2")
                .build(), l);
        Assertions.assertEquals(userService.getById(l).getEmail(), "testemail2");

    }

    @Test
    void deleteById() {
        UserDtoIn userDtoIn = UserDtoIn.builder()
                .username("testUpdate4")
                .email("a@aa.ru")
                .password("123")
                .build();
        Long l = userService.create(userDtoIn).getId();
        userService.deleteById(l);
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.getById(l);
        });
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