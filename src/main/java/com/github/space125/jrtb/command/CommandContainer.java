package com.github.space125.jrtb.command;

import com.github.space125.jrtb.command.annotation.AdminCommand;
import com.github.space125.jrtb.jrclient.JavaRushGroupClient;
import com.github.space125.jrtb.service.GroupSubService;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.StatisticService;
import com.github.space125.jrtb.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import java.util.List;

import static com.github.space125.jrtb.command.CommandName.*;
import static java.util.Objects.nonNull;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 *
 * @author Ivan Kurilov on 09.07.2021
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            JavaRushGroupClient javaRushGroupClient,
                            TelegramUserService telegramUserService,
                            List<String> admins,
                            GroupSubService groupSubService,
                            StatisticService statisticService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(ADMIN_HELP.getCommandName(), new AdminHelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, statisticService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, javaRushGroupClient, groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .put(DEL_GROUP_SUB.getCommandName(), new DelGroupSubCommand(sendBotMessageService, groupSubService, telegramUserService))
                .build();

        this.admins = admins;
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command findCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            }
            return unknownCommand;
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
