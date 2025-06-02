package ru.webrise.controller.dto.subscription.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubscriptionDto {

    private UUID userId;
    private UUID platformId;
    private String platformAccountId;

}
