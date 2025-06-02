package ru.webrise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.webrise.controller.dto.platform.PlatformDto;
import ru.webrise.converter.PlatformMapper;
import ru.webrise.service.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final PlatformMapper platformMapper;

    @GetMapping("/top/{limit}")
    public ResponseEntity<List<PlatformDto>> getTop(@PathVariable int limit) {
        return ResponseEntity.ok(
            platformMapper.entityToDto(
                subscriptionService.getTopByLimit(limit)
            )
        );
    }

}
