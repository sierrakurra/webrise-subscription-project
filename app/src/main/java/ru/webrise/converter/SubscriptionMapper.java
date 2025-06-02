package ru.webrise.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.webrise.controller.dto.subscription.SubscriptionDto;
import ru.webrise.model.Subscription;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {

    List<SubscriptionDto> entityToDto(List<Subscription> subscriptions);
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "platformId", source = "platform.id")
    SubscriptionDto entityToDto(Subscription subscription);

}
