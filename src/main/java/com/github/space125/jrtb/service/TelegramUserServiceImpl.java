package com.github.space125.jrtb.service;

import com.github.space125.jrtb.repository.TelegramUserRepository;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TelegramUserService}
 *
 * @author Ivan Kurilov on 28.07.2021
 */
@Service
@AllArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public void save(TelegramUser telegramUser) {
        telegramUserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> findAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(Long chatId) {
        return telegramUserRepository.findById(chatId);
    }

    @Override
    public List<TelegramUser> findAllInActiveUsers() {
        return telegramUserRepository.findAllByActiveFalse();
    }
}
