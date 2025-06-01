package ru.webrise.controller.dto.user.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto extends UpdateUserRequestDto {

    private String email;
    private String password;

}
