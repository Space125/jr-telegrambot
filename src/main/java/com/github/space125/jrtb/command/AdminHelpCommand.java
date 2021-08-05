package com.github.space125.jrtb.command;

import com.github.space125.jrtb.service.SendBotMessageService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.space125.jrtb.command.CommandName.STAT;
import static com.github.space125.jrtb.command.CommandUtils.getChatId;

/**
 * Command Admin Help implementation of {@link Command}
 *
 * @author Ivan Kurilov on 05.08.2021
 */
@AllArgsConstructor
public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE = String.format("✨<b>Доступные команды админа</b>✨\n\n"
                    + "<b>Получить статистику</b>\n"
                    + "%s - статистика бота\n",
            STAT.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), ADMIN_HELP_MESSAGE);
    }
}
