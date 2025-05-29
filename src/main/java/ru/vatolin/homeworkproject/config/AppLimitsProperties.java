package ru.vatolin.homeworkproject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.limits")
public class AppLimitsProperties {
    private long methodTimeoutMs;
}
