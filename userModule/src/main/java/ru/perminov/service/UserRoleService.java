package ru.perminov.service;

import ru.perminov.dto.RoleDto;
import java.util.List;


public interface UserRoleService {

    List<RoleDto> getUserRole(Long id);

    List<RoleDto> addRoleByUser(Long userId, Long roleId);

    List<RoleDto> deleteRoleByUser(Long userId, Long roleId);
}
