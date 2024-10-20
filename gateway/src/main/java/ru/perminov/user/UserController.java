package ru.perminov.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perminov.dto.RoleDto;
import ru.perminov.dto.user.UserDtoIn;
import ru.perminov.dto.user.UserDtoOut;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserClient userClient;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll() {
        log.info("Пришел GET запрос на получение всех пользователей");
        return userClient.getAllUsers();


    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getById(@PathVariable @Positive Long id) {
        log.info("Пришел GET запрос на получения пользователя с id: {}", id);
        return userClient.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@Valid @RequestBody UserDtoIn user) {
        log.info("Пришел POST запрос на создание пользователя: {}", user);
        return userClient.create(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> update(@Valid @RequestBody UserDtoIn user, @Positive @PathVariable Long id) {
        log.info("Пришел PUT запрос {} на обновление пользователя ид: {}", user, id);
        return userClient.update(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Positive Long id) {
        log.info("Пришел DELETE запрос на удаление пользователя ид: {}", id);
        userClient.deleteById(id);
    }

    @GetMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getRoleByUserId(@PathVariable @Positive Long id) {
        log.info("Пришел GET запрос на получений всех ролей пользователя ид: {}", id);
        return userClient.getUserRole(id);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addRoleByUser(@PathVariable @Positive Long userId,
                                       @PathVariable @Positive Long roleId) {
        log.info("Пришел POST запрос на добавлении роли пользователю ид: {}", userId);
        return userClient.addRoleByUser(userId, roleId);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> deleteRoleByUser(@PathVariable @Positive Long userId,
                                       @PathVariable @Positive Long roleId) {
        log.info("Пришел DELETE запрос на удалении роли пользователю ид: {}", userId);
        return userClient.deleteRoleByUser(userId, roleId);
    }
}