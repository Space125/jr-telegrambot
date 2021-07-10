package com.github.space125.jrtb.command;

import com.github.space125.jrtb.service.SendBotMessageService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command Start implementation of {@link Command}
 *
 * @author Ivan Kurilov on 09.07.2021
 */

@AllArgsConstructor
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Привет. Я Javarush Telegram Bot. Я помогу тебе быть в курсе последних "
            + "статей тех авторов, которые тебе интересны. Я еще маленький \uD83D\uDE0A и только учусь."
            + " Посмотри что я уже умею делать /help";

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
