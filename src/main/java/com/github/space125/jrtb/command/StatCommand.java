package com.github.space125.jrtb.command;

import com.github.space125.jrtb.command.annotation.AdminCommand;
import com.github.space125.jrtb.dto.StatisticDto;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.StatisticService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.github.space125.jrtb.command.CommandUtils.getChatId;

/**
 * Command Statistics implementation of {@link Command}
 *
 * @author Ivan Kurilov on 28.07.2021
 */
@AllArgsConstructor
@AdminCommand
public class StatCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final StatisticService statisticService;

    public static final String STAT_MESSAGE = "✨<b>Подготовил статистику</b>✨\n" +
            "- Количество активных пользователей: %s\n" +
            "- Количество неактивных пользователей: %s\n" +
            "- Среднее количество групп на одного пользователя: %s\n\n" +
            "<b>Информация по активным группам</b>:\n" +
            "%s";

    @Override
    public void execute(Update update) {
        StatisticDto statisticDto = statisticService.countBotStatistic();

        String collectedGroups = statisticDto.getGroupStats().stream()
                .map(group -> String.format("%s (id = %s) - %s подписчиков", group.getTitle(), group.getId(), group.getActiveUserCount()))
                .collect(Collectors.joining("\n"));

        sendBotMessageService.sendMessage(getChatId(update), String.format(STAT_MESSAGE,
                statisticDto.getActiveUserCount(),
                statisticDto.getInactiveUserCount(),
                statisticDto.getAverageGroupCountByUser(),
                collectedGroups));
    }
}
