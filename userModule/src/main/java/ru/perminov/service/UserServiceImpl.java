package ru.perminov.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.perminov.dto.RoleDto;
import ru.perminov.dto.user.UserDtoIn;
import ru.perminov.dto.user.UserDtoOut;
import ru.perminov.mapper.UserMapper;
import ru.perminov.model.User;
import ru.perminov.repositoty.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserRoleService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDtoOut> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoOut getById(Long id) {
        User user = getUser(id);
        return UserMapper.toDto(user);
    }

    @Override
    public UserDtoOut create(UserDtoIn dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserDtoOut update(UserDtoIn dto, Long id) {
        User user = getUser(id);

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        getUser(id);
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<RoleDto> getUserRole(Long userId) {
        getUser(userId);
        List<RoleDto> roleDtoList = userRepository.getRolesById(userId);
        return roleDtoList;
    }

    @Override
    public List<RoleDto> addRoleByUser(Long userId, Long roleId) {
        getUser(userId);
        userRepository.addRoleByUser(userId, roleId);
        return getUserRole(userId);
    }

    @Override
    public List<RoleDto> deleteRoleByUser(Long userId, Long roleId) {
        getUser(userId);
        userRepository.deleteRoleByUser(userId, roleId);
        return getUserRole(userId);
    }

    private User getUser (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не существует. Ид: " + id));
    }
}
