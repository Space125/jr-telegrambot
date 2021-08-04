package com.github.space125.jrtb.command;

import org.junit.jupiter.api.DisplayName;

/**
 * @author Ivan Kurilov on 10.07.2021
 */
@DisplayName("Unit-level testing for HelpCommand")
class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HelpCommand.HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
