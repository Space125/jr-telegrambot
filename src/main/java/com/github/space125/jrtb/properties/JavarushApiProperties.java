package com.github.space125.jrtb.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ivan Kurilov on 29.07.2021
 */
@Configuration
@ConfigurationProperties(prefix = "javarush.api")
@Data
public class JavarushApiProperties {
    private String path;
}
