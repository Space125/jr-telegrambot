package com.github.space125.jrtb.command;

import com.github.space125.jrtb.jrclient.JavaRushGroupClient;
import com.github.space125.jrtb.service.GroupSubService;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.StatisticService;
import com.github.space125.jrtb.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Ivan Kurilov on 10.07.2021
 */
@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient javaRushGroupClient = Mockito.mock(JavaRushGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        StatisticService statisticService = Mockito.mock(StatisticService.class);
        commandContainer = new CommandContainer(
                sendBotMessageService,
                javaRushGroupClient,
                telegramUserService,
                Collections.singletonList("username"),
                groupSubService, statisticService);
    }

    @Test
    void shouldGetTheExistingCommand() {
        //given-when
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.findCommand(commandName.getCommandName(), "username");
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    void shouldReturnedUnknownCommand() {
        //given
        String unknownCommand = "/unknownCommand";

        //when
        Command command = commandContainer.findCommand(unknownCommand, "username");

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}