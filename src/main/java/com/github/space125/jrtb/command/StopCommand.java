package com.github.space125.jrtb.command;

import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command Stop implementation of {@link Command}
 *
 * @author Ivan Kurilov on 09.07.2021
 */
@AllArgsConstructor
public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Деактивировал все ваши подписки \uD83D\uDE1F.";

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, STOP_MESSAGE);
        telegramUserService.findByChatId(chatId).ifPresent(
                user -> {
                    user.setActive(false);
                    telegramUserService.save(user);
                }
        );
    }
}
