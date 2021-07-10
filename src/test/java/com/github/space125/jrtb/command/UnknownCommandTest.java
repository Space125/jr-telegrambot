package com.github.space125.jrtb.command;

import org.junit.jupiter.api.DisplayName;

/**
 * @author Ivan Kurilov on 10.07.2021
 */
@DisplayName("Unit-level testing for UnknownCommand")
class UnknownCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return "/unknownCommand";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}