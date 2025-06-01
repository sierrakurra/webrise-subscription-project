package ru.webrise.controller.dto.user.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    private String surname;
    private String name;

}
