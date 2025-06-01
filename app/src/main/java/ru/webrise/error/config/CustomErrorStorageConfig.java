package ru.webrise.error.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom-error.storage")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomErrorStorageConfig {

    private String location = "custom-errors.json";

}
