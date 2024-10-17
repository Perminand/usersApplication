package ru.perminov.dto;

import lombok.*;
import ru.perminov.model.Role;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoOut {

    private Long id;

    private String username;

    private String email;

    private Set<Role> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
