package ru.perminov.mappers;

import ru.perminov.dto.UserDtoIn;
import ru.perminov.dto.UserDtoOut;
import ru.perminov.model.User;

public class UserMapper {

    public static UserDtoOut toDto(User user) {
        return UserDtoOut.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

    }

    public static User toEntity(UserDtoIn dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }
}
