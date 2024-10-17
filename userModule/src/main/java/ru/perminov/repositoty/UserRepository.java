package ru.perminov.repositoty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.dto.RoleDto;
import ru.perminov.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT r.id, r.name, r.description FROM users AS u " +
            "JOIN user_role As ur ON u.id = ur.user_id " +
            "JOIN roles AS r ON ur.role_id = r.id WHERE u.id = :id", nativeQuery = true)
    List<RoleDto> getRolesById(Long id);

    @Query(value = "INSERT INTO user_role (user_id, role_id) VALUES (:userId, :roleId)", nativeQuery = true)
    void addRoleByUser(Long userId, Long roleId);

    @Query(value = "DELETE FROM user_role WHERE user_id= :userId AND role_id=:roleId", nativeQuery = true)
    void deleteRoleByUser(Long userId, Long roleId);
}
