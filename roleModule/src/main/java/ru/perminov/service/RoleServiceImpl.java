package ru.perminov.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.perminov.dto.RoleDto;
import ru.perminov.mapper.RoleMapper;
import ru.perminov.model.Role;
import ru.perminov.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional()
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto getById(Long id) {
        Role role = getRole(id);
        return RoleMapper.toDto(role);
    }

    @Override
    public RoleDto create(RoleDto dto) {
        Role role = RoleMapper.toEntity(dto);
        roleRepository.save(role);
        return RoleMapper.toDto(role);
    }

    @Override
    public RoleDto update(RoleDto dto, Long id) {
        Role role = getRole(id);
        if(dto.getName() != null) {
            role.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            role.setDescription(dto.getDescription());
        }
        roleRepository.save(role);
        return RoleMapper.toDto(role);
    }

    @Override
    public void deleteById(Long id) {
        getRole(id);
        roleRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    private Role getRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Нет роли с ид: " + id));
    }
}
