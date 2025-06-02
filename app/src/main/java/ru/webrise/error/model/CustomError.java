package ru.webrise.error.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomError {

    private String code;
    private String message;
    private Integer status;

}
