package ru.perminov.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perminov.dto.UserDtoIn;
import ru.perminov.dto.UserDtoOut;
import ru.perminov.model.User;
import ru.perminov.services.UserService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDtoOut> getAll() {
        log.info("Пришел GET запрос на получение всех пользователей");
        return userService.getAll();


    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoOut getById(@PathVariable @Positive Long id) {
        log.info("Пришел GET запрос на получения пользователя с id: {}", id);
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoOut create(@Valid @RequestBody UserDtoIn user) {
        log.info("Пришел POST запрос на создание пользователя: {}", user);
        return userService.create(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoOut update(@Valid @RequestBody UserDtoIn user, @Positive @PathVariable Long id) {
        log.info("Пришел PUT запрос на обновление пользователя: {}, ид: {}", user, id);
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Positive Long id) {
        log.info("Пришел DELETE запрос на удаление пользователя ид: {}", id);
        userService.deleteById(id);
    }
}