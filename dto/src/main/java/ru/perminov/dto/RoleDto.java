package ru.perminov.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.perminov.dto.marker.Create;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    @NotBlank(groups = Create.class ,message = "Имя роли не может быть пустым")
    private String name;

    private String description;


}
