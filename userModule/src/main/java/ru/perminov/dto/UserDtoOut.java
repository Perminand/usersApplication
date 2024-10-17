package ru.perminov.dto;

import lombok.*;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
