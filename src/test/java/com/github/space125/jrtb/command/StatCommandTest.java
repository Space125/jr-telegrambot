package com.github.space125.jrtb.command;

import com.github.space125.jrtb.dto.GroupStatDto;
import com.github.space125.jrtb.dto.StatisticDto;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.github.space125.jrtb.command.AbstractCommandTest.prepareUpdate;
import static com.github.space125.jrtb.command.CommandName.STAT;
import static com.github.space125.jrtb.command.StatCommand.STAT_MESSAGE;
import static java.util.Collections.singletonList;

/**
 * @author Ivan Kurilov on 28.07.2021
 */
@DisplayName("Unit-level testing for StatCommand")
class StatCommandTest {

    private SendBotMessageService sendBotMessageService;
    private Command statCommand;
    private StatisticService statisticService;

    @BeforeEach
    void init() {
        sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        statisticService = Mockito.mock(StatisticService.class);
        statCommand = new StatCommand(sendBotMessageService, statisticService);
    }

    @Test
    void shouldProperlySendMessage() {
        //given
        Long chatId = 1234567L;
        GroupStatDto groupStat = new GroupStatDto(1, "gs1", 1);
        StatisticDto statisticDto = new StatisticDto(1, 1, singletonList(groupStat), 2.5);
        Mockito.when(statisticService.countBotStatistic()).thenReturn(statisticDto);

        //when
        statCommand.execute(prepareUpdate(chatId, STAT.getCommandName()));

        //then
        Mockito.verify(sendBotMessageService).sendMessage(chatId, String.format(STAT_MESSAGE,
                statisticDto.getActiveUserCount(),
                statisticDto.getInactiveUserCount(),
                statisticDto.getAverageGroupCountByUser(),
                String.format("%s (id = %s) - %s подписчиков", groupStat.getTitle(), groupStat.getId(),
                        groupStat.getActiveUserCount())));
    }
}
