package ru.webrise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.webrise.controller.dto.subscription.SubscriptionDto;
import ru.webrise.controller.dto.subscription.request.CreateSubscriptionDto;
import ru.webrise.converter.SubscriptionMapper;
import ru.webrise.service.SubscriptionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class UserSubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getUserSubscriptions(@PathVariable UUID userId) {
        return ResponseEntity.ok(
            subscriptionMapper.entityToDto(
                subscriptionService.getSubscriptionsByUserId(userId)
            )
        );
    }

    @PostMapping
    public ResponseEntity<SubscriptionDto> createUserSubscription(@PathVariable UUID userId,
                                                  @RequestBody CreateSubscriptionDto request) {
        return ResponseEntity.ok(
            subscriptionMapper.entityToDto(
                subscriptionService.createSubscription(userId, request)
            )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserSubscription(@PathVariable UUID userId, @PathVariable UUID id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

}
