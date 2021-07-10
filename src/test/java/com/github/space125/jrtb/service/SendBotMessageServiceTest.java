package com.github.space125.jrtb.service;

import com.github.space125.jrtb.bot.JavaRushTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Ivan Kurilov on 10.07.2021
 */
@DisplayName("Unit-level testing for SendBotMessageService")
class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private JavaRushTelegramBot javaRushTelegramBot;

    @BeforeEach
    public void init() {
        javaRushTelegramBot = Mockito.mock(JavaRushTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(javaRushTelegramBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(javaRushTelegramBot).execute(sendMessage);
    }
}