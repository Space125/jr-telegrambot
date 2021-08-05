package com.github.space125.jrtb.service;

import com.github.space125.jrtb.repository.entity.TelegramUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link TelegramUser} entity.
 *
 * @author Ivan Kurilov on 28.07.2021
 */
public interface TelegramUserService {

    /**
     * Save provided {@link TelegramUser} entity.
     *
     * @param telegramUser provided telegram user.
     */
    void save(TelegramUser telegramUser);

    /**
     * Find all active {@link TelegramUser}.
     *
     * @return the list of the active {@link TelegramUser} objects.
     */
    List<TelegramUser> findAllActiveUsers();

    /**
     * Find {@link TelegramUser} by chatId.
     *
     * @param chatId provided Chat Id
     * @return {@link TelegramUser} with provided chat ID or null otherwise.
     */
    Optional<TelegramUser> findByChatId(String chatId);

    /**
     * Find all inactive {@link TelegramUser}.
     *
     * @return the list of the inactive {@link TelegramUser} objects.
     */
    List<TelegramUser> findAllInActiveUsers();
}
