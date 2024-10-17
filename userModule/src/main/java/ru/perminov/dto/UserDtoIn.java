package ru.perminov.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.perminov.marker.Create;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoIn {

    @NotBlank(groups = Create.class ,message = "Имя пользователя не может быть пустым")
    private String username;

    @NotBlank(groups = Create.class, message = "email пользователя не может быть пустым")
    @Email(message = "email не корректен")
    private String email;

    @NotBlank(groups = Create.class, message = "пароль пользователя не может быть пустым")
    private String password;
}
