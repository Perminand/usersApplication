package ru.perminov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
