package ru.webrise.converter;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.webrise.controller.dto.platform.PlatformDto;
import ru.webrise.model.Platform;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlatformMapper {

    List<PlatformDto> entityToDto(List<Platform> platforms);
    PlatformDto entityToDto(Platform platform);

}
