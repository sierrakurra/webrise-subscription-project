package ru.webrise.controller.dto.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DetailedUserDto extends UserDto {

    private String email;
    private LocalDateTime createdAt;

}
