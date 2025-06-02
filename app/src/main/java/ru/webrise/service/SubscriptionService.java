package ru.webrise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.controller.dto.subscription.request.CreateSubscriptionDto;
import ru.webrise.error.constant.Error;
import ru.webrise.error.exception.CommonException;
import ru.webrise.model.Platform;
import ru.webrise.model.Subscription;
import ru.webrise.model.User;
import ru.webrise.repository.SubscriptionRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final PlatformService platformService;

    public List<Platform> getTopByLimit(int limit) {
        return subscriptionRepository.findTopPlatformSubscriptionsByLimit(limit);
    }

    @Transactional(readOnly = true)
    public List<Subscription> getSubscriptionsByUserId(UUID userId) {
        return subscriptionRepository.findAllByUserId(userId);
    }

    @Transactional
    public Subscription createSubscription(UUID userId, CreateSubscriptionDto request) {
        Platform platform = platformService.getPlatformById(request.getPlatformId());
        User user = userService.getUserById(userId);

        if (subscriptionRepository.existsByUserIdAndPlatformIdAndPlatformAccountId(
                userId, request.getPlatformId(), request.getPlatformAccountId())) {
            throw new CommonException(Error.SUBSCRIPTION_ALREADY_EXISTS_ERROR)
                    .withParameters(userId, platform.getId(), request.getPlatformAccountId());
        }


        Subscription subscription = Subscription.builder()
                .platform(platform)
                .user(user)
                .platformAccountId(request.getPlatformAccountId())
                .build();

        return subscriptionRepository.save(subscription);
    }


    @Transactional
    public void deleteSubscription(UUID subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

}
