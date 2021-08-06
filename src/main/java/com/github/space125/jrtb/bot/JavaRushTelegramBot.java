package com.github.space125.jrtb.bot;

import com.github.space125.jrtb.command.CommandContainer;
import com.github.space125.jrtb.jrclient.JavaRushGroupClient;
import com.github.space125.jrtb.service.GroupSubService;
import com.github.space125.jrtb.service.SendBotMessageServiceImpl;
import com.github.space125.jrtb.service.StatisticService;
import com.github.space125.jrtb.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.github.space125.jrtb.command.CommandName.NO;
import static org.apache.commons.lang3.StringUtils.SPACE;

/**
 * Telegram Bot for Javarush Community from Javarush community.
 *
 * @author Ivan Kurilov on 09.07.2021
 */
@Component
public class JavaRushTelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    @Autowired
    public JavaRushTelegramBot(TelegramUserService telegramUserService,
                               JavaRushGroupClient javaRushGroupClient,
                               @Value("#{'${bot.admins}'.split(',')}") List<String> admins,
                               GroupSubService groupSubService,
                               StatisticService statisticService) {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this),
                javaRushGroupClient, telegramUserService, admins, groupSubService, statisticService);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String username = update.getMessage().getFrom().getUserName();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(SPACE)[0].toLowerCase();
                commandContainer.findCommand(commandIdentifier, username).execute(update);
            } else {
                commandContainer.findCommand(NO.getCommandName(), username).execute(update);
            }
        }
    }
}
