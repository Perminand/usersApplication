package ru.perminov.service;

import ru.perminov.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();

    RoleDto getById(Long id);

    RoleDto create(RoleDto dto);

    RoleDto update(RoleDto dto, Long id);

    void deleteById(Long id);

    void deleteAll();
}
