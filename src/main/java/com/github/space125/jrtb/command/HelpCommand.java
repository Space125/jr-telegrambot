package com.github.space125.jrtb.command;

import com.github.space125.jrtb.service.SendBotMessageService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.space125.jrtb.command.CommandName.*;
import static com.github.space125.jrtb.command.CommandUtils.getChatId;

/**
 * Command Help implementation of {@link Command}
 *
 * @author Ivan Kurilov on 09.07.2021
 */
@AllArgsConstructor
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨<b>Доступные команды</b>✨\n\n"

                    + "<b>Начать\\закончить работу с ботом:</b>\n"
                    + "%s - начать/восстановить работу со мной\n"
                    + "%s - приостановить работу со мной\n\n"

                    + "<b>Работа с подписками на группы:</b>\n"
                    + "%s - подписаться на группу статей\n"
                    + "%s - отписаться от группы статей\n"
                    + "%s - получить список групп, на которые вы подписаны\n\n"

                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), STOP.getCommandName(),
            ADD_GROUP_SUB.getCommandName(), DEL_GROUP_SUB.getCommandName(), LIST_GROUP_SUB.getCommandName(),
            HELP.getCommandName());

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), HELP_MESSAGE);
    }
}
