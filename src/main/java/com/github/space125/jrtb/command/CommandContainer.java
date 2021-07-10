package com.github.space125.jrtb.command;

import com.github.space125.jrtb.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.github.space125.jrtb.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 *
 * @author Ivan Kurilov on 09.07.2021
 */
public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
