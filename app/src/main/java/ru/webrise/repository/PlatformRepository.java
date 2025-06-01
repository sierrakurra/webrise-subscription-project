package ru.webrise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webrise.model.Platform;

import java.util.UUID;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, UUID> {
}
