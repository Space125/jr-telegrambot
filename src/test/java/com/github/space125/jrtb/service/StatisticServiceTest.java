package com.github.space125.jrtb.service;

import com.github.space125.jrtb.dto.GroupStatDto;
import com.github.space125.jrtb.dto.StatisticDto;
import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static java.util.Collections.singletonList;

/**
 * @author Ivan Kurilov on 05.08.2021
 */
@DisplayName("Unit-level testing for StatisticService")
class StatisticServiceTest {

    private GroupSubService groupSubService;
    private TelegramUserService telegramUserService;
    private StatisticService statisticService;

    @BeforeEach
    void init(){
        groupSubService = Mockito.mock(GroupSubService.class);
        telegramUserService = Mockito.mock(TelegramUserService.class);
        statisticService = new StatisticServiceImpl(telegramUserService, groupSubService);
    }

    @Test
    void shouldProperlySendStatDto(){
        //given
        Mockito.when(telegramUserService.findAllInActiveUsers()).thenReturn(singletonList(new TelegramUser()));
        TelegramUser activeUser = new TelegramUser();
        activeUser.setGroupSubs(singletonList(new GroupSub()));
        Mockito.when(telegramUserService.findAllActiveUsers()).thenReturn(singletonList(activeUser));
        GroupSub groupSub = new GroupSub();
        groupSub.setId(1);
        groupSub.setTitle("g1");
        groupSub.setUsers(singletonList(new TelegramUser()));
        Mockito.when(groupSubService.findAll()).thenReturn(singletonList(groupSub));

        //when
        StatisticDto statisticDto = statisticService.countBotStatistic();

        //then
        Assertions.assertNotNull(statisticDto);
        Assertions.assertEquals(1, statisticDto.getActiveUserCount());
        Assertions.assertEquals(1, statisticDto.getInactiveUserCount());
        Assertions.assertEquals(1.0, statisticDto.getAverageGroupCountByUser());
        Assertions.assertEquals(singletonList(new GroupStatDto(groupSub.getId(), groupSub.getTitle(), groupSub.getUsers().size())),
                statisticDto.getGroupStats());
    }

}