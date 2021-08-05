package com.github.space125.jrtb.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.space125.jrtb.command.AdminHelpCommand.ADMIN_HELP_MESSAGE;
import static com.github.space125.jrtb.command.CommandName.ADMIN_HELP;

/**
 * @author Ivan Kurilov on 05.08.2021
 */
@DisplayName("Unit-level testing for AdminHelpCommand")
class AdminHelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return ADMIN_HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ADMIN_HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new AdminHelpCommand(sendBotMessageService);
    }
}