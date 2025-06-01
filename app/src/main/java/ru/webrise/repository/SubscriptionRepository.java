package ru.webrise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.webrise.model.Subscription;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
