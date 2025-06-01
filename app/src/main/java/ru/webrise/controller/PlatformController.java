package ru.webrise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webrise.model.Platform;
import ru.webrise.repository.PlatformRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/platforms")
public class PlatformController {

    private final PlatformRepository platformRepository;

    @GetMapping
    ResponseEntity<List<Platform>> getAll() {
        return ResponseEntity.ok(platformRepository.findAll());
    }

}
