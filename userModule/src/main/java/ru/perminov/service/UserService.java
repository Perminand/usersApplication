package ru.perminov.service;

import ru.perminov.dto.user.UserDtoIn;
import ru.perminov.dto.user.UserDtoOut;

import java.util.List;

public interface UserService {
    List<UserDtoOut> getAll();

    UserDtoOut getById(Long id);

    UserDtoOut create(UserDtoIn user);

    UserDtoOut update(UserDtoIn user, Long id);

    void deleteById(Long id);

    void deleteAll();

}
