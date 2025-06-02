package ru.webrise.controller.dto.subscription;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDto {

    private UUID id;
    private UUID userId;
    private UUID platformId;
    private String platformAccountId;

}
