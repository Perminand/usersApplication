package ru.perminov.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perminov.dto.RoleDto;
import ru.perminov.service.RoleService;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> getAll() {
        log.info("Пришел GET запрос на получение всех ролей");
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto getById(@PathVariable @Positive Long id) {
        log.info("Пришел GET запрос на получение роли по ид: {}", id);
        return roleService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto create(@RequestBody @Valid RoleDto dto) {
        log.info("Пришел POST запрос на создании роли: {}", dto);
        return roleService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto update(@RequestBody @Valid RoleDto dto, @PathVariable @Positive Long id) {
        log.info("Пришел PUT запрос {} на изменение роли ид: {}", dto, id);
        return roleService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable @Positive Long id) {
        log.info("Пришел DELETE запрос на удаление роли ид: {}", id);
        roleService.deleteById(id);
    }

}
