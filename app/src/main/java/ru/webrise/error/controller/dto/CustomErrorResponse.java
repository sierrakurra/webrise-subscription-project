package ru.webrise.error.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

    private String code;
    private String message;
    private Integer status;
    private String uri;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
