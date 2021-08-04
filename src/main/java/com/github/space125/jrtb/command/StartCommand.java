package com.github.space125.jrtb.command;

import com.github.space125.jrtb.repository.entity.TelegramUser;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.space125.jrtb.command.CommandUtils.getChatId;

/**
 * Command Start implementation of {@link Command}
 *
 * @author Ivan Kurilov on 09.07.2021
 */
@AllArgsConstructor
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет. Я Javarush Telegram Bot. Я помогу тебе быть в курсе последних "
            + "статей тех авторов, которые тебе интересны. Я еще маленький \uD83D\uDE0A и только учусь."
            + " Посмотри что я уже умею делать /help";

    @Override
    public void execute(Update update) {
        String chatId = getChatId(update);
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setChatId(chatId);
                    telegramUser.setActive(true);
                    telegramUserService.save(telegramUser);
                }
        );
        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
