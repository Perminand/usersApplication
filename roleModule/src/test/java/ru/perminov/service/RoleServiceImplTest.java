package ru.perminov.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.perminov.dto.RoleDto;

@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        roleService.deleteAll();
    }

    @Test
    void getAll() {
        roleService.create(RoleDto.builder().name("test").description("test").build());
        roleService.create(RoleDto.builder().name("test2").description("test2").build());
        Assertions.assertEquals(roleService.getAll().size(), 2);
    }

    @Test
    void getById() {
        RoleDto roleDto = RoleDto.builder().name("test").description("test").build();
        Long l = roleService.create(roleDto).getId();
        Assertions.assertEquals(roleService.getById(l).getName(), roleDto.getName());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            roleService.getById(-1L);
        });
    }


    @Test
    void create() {
        RoleDto roleDto = RoleDto.builder().name("test").description("test").build();
        Long l = roleService.create(roleDto).getId();
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            roleService.create(RoleDto.builder().name("test").description("test").build());
        });
    }

    @Test
    void update() {
        RoleDto roleDto = RoleDto.builder().name("test").description("test").build();
        Long l = roleService.create(roleDto).getId();
        roleService.update(RoleDto.builder().name("testUpdate").build(), l);
        Assertions.assertEquals(roleService.getById(l).getName(), "testUpdate");
    }

    @Test
    void deleteById() {
        RoleDto roleDto = RoleDto.builder().name("test").description("test").build();
        Long l = roleService.create(roleDto).getId();
        roleService.deleteById(l);
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            roleService.getById(l);
        });

    }
}