package com.github.space125.jrtb.service;

import com.github.space125.jrtb.bot.JavaRushTelegramBot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;


/**
 * Implementation of {@link SendBotMessageService} interface.
 *
 * @author Ivan Kurilov on 09.07.2021
 */
@Service
@AllArgsConstructor
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final JavaRushTelegramBot JavaRushTelegramBot;

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            JavaRushTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String chatId, List<String> messages) {
        if (isEmpty(messages)) return;

        messages.forEach(message -> sendMessage(chatId, message));
    }
}
