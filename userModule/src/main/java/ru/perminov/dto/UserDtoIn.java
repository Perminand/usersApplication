package ru.perminov.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoIn {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    private String username;

    @NotBlank(message = "email пользователя не может быть пустым")
    @Email(message = "email не корректен")
    private String email;

    @NotBlank(message = "пароль пользователя не может быть пустым")
    private String password;
}
