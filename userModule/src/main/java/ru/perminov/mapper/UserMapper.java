package ru.perminov.mapper;

import ru.perminov.dto.user.UserDtoIn;
import ru.perminov.dto.user.UserDtoOut;
import ru.perminov.model.User;

public class UserMapper {

    public static UserDtoOut toDto(User user) {
        return UserDtoOut.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

    }

    public static User toEntity(UserDtoIn dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }
}
