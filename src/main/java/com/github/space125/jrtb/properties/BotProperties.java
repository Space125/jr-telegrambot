package com.github.space125.jrtb.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Property for Telegram Bot
 *
 * @author Ivan Kurilov on 09.07.2021
 */
@Configuration
@ConfigurationProperties(prefix = "bot")
@Data
public class BotProperties {

    /**
     * Username for the registration Telegram Bot
     */
    private String username;

    /**
     * Token of the registration.
     */
    private String token;


    /**
     * Recount of new articles
     */
    private String recountNewArticle;


    private String admins;

}
