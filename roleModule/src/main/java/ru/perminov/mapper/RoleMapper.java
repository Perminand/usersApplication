package ru.perminov.mapper;

import ru.perminov.dto.RoleDto;
import ru.perminov.model.Role;

public class RoleMapper {

    public static Role toEntity (RoleDto dto) {
        return Role.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public static RoleDto toDto (Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .build();
    }
}
