package com.github.space125.jrtb.command;

import com.github.space125.jrtb.bot.JavaRushTelegramBot;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.SendBotMessageServiceImpl;
import com.github.space125.jrtb.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Abstract class for testing {@link Command}s
 *
 * @author Ivan Kurilov on 10.07.2021
 */
abstract class AbstractCommandTest {

    protected JavaRushTelegramBot javaRushTelegramBot = Mockito.mock(JavaRushTelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(javaRushTelegramBot);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    void shouldProperlyExecuteCommand() throws TelegramApiException {
        //given
        Long chatId = 1234567824356L;

        Update update = prepareUpdate(chatId, getCommandName());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        sendMessage.setText(getCommandMessage());

        //when
        getCommand().execute(update);

        //then
        Mockito.verify(javaRushTelegramBot).execute(sendMessage);
    }

    public static Update prepareUpdate(Long chatId, String commandName) {
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(commandName);
        update.setMessage(message);
        return update;
    }
}
