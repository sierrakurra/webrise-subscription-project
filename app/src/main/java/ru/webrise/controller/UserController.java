package ru.webrise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.webrise.controller.dto.user.DetailedUserDto;
import ru.webrise.controller.dto.user.UserDto;
import ru.webrise.controller.dto.user.request.CreateUserRequestDto;
import ru.webrise.controller.dto.user.request.UpdateUserRequestDto;
import ru.webrise.converter.UserMapper;
import ru.webrise.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userMapper.entityToDto(userService.getUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedUserDto> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userMapper.entityToDetailedDto(userService.getUserById(id)));
    }

    @PostMapping
    public ResponseEntity<DetailedUserDto> createUser(@RequestBody CreateUserRequestDto request) {
        return ResponseEntity.ok(userMapper.entityToDetailedDto(
                userService.create(userMapper.dtoToEntity(request))
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedUserDto> updateUser(@PathVariable("id") UUID id,
                                                      @RequestBody UpdateUserRequestDto request) {
        return ResponseEntity.ok(userMapper.entityToDetailedDto(
                userService.update(userMapper.dtoToEntity(request, id))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
