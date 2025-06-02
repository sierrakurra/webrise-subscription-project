package ru.webrise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.webrise.model.Platform;
import ru.webrise.model.Subscription;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    List<Subscription> findAllByUserId(UUID userId);
    boolean existsByUserIdAndPlatformIdAndPlatformAccountId(UUID userId, UUID platformId, String platformAccountId);

    @Query(value = """
        SELECT p.* FROM platforms p
        WHERE p.id IN (
            SELECT s.platform_id FROM subscriptions s
            GROUP BY s.platform_id
            ORDER BY COUNT(s.platform_id) DESC
            LIMIT :limit
        )
    """, nativeQuery = true)
    List<Platform> findTopPlatformSubscriptionsByLimit(@Param("limit") int limit);

}
