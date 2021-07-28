package com.github.space125.jrtb.command;

import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command Statistics implementation of {@link Command}
 *
 * @author Ivan Kurilov on 28.07.2021
 */
@AllArgsConstructor
public class StatCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STAT_MESSAGE = "JavaRush Telegram Bot используют %s пользователей";

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.retrieveAllActiveUsers().size();
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, String.format(STAT_MESSAGE, activeUserCount));
    }
}
