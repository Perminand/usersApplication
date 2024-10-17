package ru.perminov.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.perminov.dto.UserDtoIn;
import ru.perminov.dto.UserDtoOut;
import ru.perminov.mappers.UserMapper;
import ru.perminov.model.User;
import ru.perminov.repositoty.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserDtoOut> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserDtoOut getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден ид: " + id));
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

    private User getUser (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не существует. Ид: " + id));
    }
}
