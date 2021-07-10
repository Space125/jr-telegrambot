package com.github.space125.jrtb.command;

import org.junit.jupiter.api.DisplayName;

/**
 * @author Ivan Kurilov on 10.07.2021
 */
@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandName.NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NoCommand.NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
