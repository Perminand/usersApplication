package ru.perminov.repositoty;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.perminov.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
