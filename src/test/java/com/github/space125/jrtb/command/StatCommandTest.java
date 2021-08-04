package com.github.space125.jrtb.command;

import org.junit.jupiter.api.DisplayName;

/**
 * @author Ivan Kurilov on 28.07.2021
 */
@DisplayName("Unit-level testing for StatCommand")
class StatCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandName.STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(StatCommand.STAT_MESSAGE,0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(sendBotMessageService, telegramUserService);
    }
}
