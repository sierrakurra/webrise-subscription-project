package ru.webrise.converter;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.webrise.controller.dto.user.DetailedUserDto;
import ru.webrise.controller.dto.user.UserDto;
import ru.webrise.controller.dto.user.request.CreateUserRequestDto;
import ru.webrise.controller.dto.user.request.UpdateUserRequestDto;
import ru.webrise.model.User;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @IterableMapping(qualifiedByName = "userToUserDto")
    List<UserDto> entityToDto(List<User> users);
    @Named("userToUserDto")
    UserDto entityToDto(User user);
    DetailedUserDto entityToDetailedDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User dtoToEntity(CreateUserRequestDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "surname", source = "dto.surname")
    @Mapping(target = "name", source = "dto.name")
    User dtoToEntity(UpdateUserRequestDto dto, UUID id);

}
