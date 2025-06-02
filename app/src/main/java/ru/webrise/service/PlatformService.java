package ru.webrise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.webrise.error.constant.Error;
import ru.webrise.error.exception.CommonException;
import ru.webrise.model.Platform;
import ru.webrise.repository.PlatformRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    @Transactional(readOnly = true)
    public Platform getPlatformById(UUID id) {
        return platformRepository.findById(id)
                .orElseThrow(
                        () -> new CommonException(Error.PLATFORM_NOT_FOUND_ERROR)
                                    .withParameters(id)
                );
    }

}
