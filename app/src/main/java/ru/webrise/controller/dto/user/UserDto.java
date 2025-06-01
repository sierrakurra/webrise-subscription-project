package ru.webrise.controller.dto.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String surname;
    private String name;

}
