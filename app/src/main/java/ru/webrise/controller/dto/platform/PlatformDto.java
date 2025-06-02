package ru.webrise.controller.dto.platform;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatformDto {

    private UUID id;
    private String name;

}
