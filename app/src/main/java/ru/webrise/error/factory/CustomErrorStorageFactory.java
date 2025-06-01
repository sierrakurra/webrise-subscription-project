package ru.webrise.error.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.webrise.error.config.CustomErrorStorageConfig;
import ru.webrise.error.model.CustomError;
import ru.webrise.error.storage.CustomErrorStorage;
import ru.webrise.error.storage.impl.CustomErrorStorageImpl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CustomErrorStorageFactory {

    private CustomErrorStorageImpl customErrorStorage = new CustomErrorStorageImpl();
    private final CustomErrorStorageConfig customErrorStorageConfig;
    private final ObjectMapper objectMapper;

    @Bean
    public CustomErrorStorage customErrorStorage() {

        log.info("Custom error storage initialization");
        try {
            initStorage();
        } catch (IOException e) {
            throw new RuntimeException("Custom error storage initialization failed", e);
        }
        log.info("Custom error storage initialized. Found {} custom errors", customErrorStorage.getSize());

        return customErrorStorage;
    }

    void initStorage() throws IOException {
        Resource storageResource = new ClassPathResource(customErrorStorageConfig.getLocation());
        if (!storageResource.exists()) {
            log.info("Custom error storage resource not found");
            return;
        }

        List<File> storageRelatedFiles = new LinkedList<>();

        File resourceFile = storageResource.getFile();
        enrichFiles(resourceFile, storageRelatedFiles);

        for (File file : storageRelatedFiles) {
            initFromFile(file);
        }
    }

    void enrichFiles(File rootFile, List<File> files) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File directoryFile : directoryFiles) {
                    enrichFiles(directoryFile, files);
                }
            }
        } else {
            files.add(rootFile);
        }
    }

    void initFromFile(File file) throws IOException {
        List<CustomError> customErrors = objectMapper.readValue(file, new TypeReference<List<CustomError>>() {});
        for (CustomError customError : customErrors) {
            customErrorStorage.put(customError);
        }
    }

}
