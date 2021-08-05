package com.github.space125.jrtb.service;

import com.github.space125.jrtb.dto.GroupStatDto;
import com.github.space125.jrtb.dto.StatisticDto;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Implementation of {@link StatisticService}
 *
 * @author Ivan Kurilov on 05.08.2021
 */
@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final TelegramUserService telegramUserService;
    private final GroupSubService groupSubService;

    @Override
    public StatisticDto countBotStatistic() {
        List<GroupStatDto> groupStats = groupSubService.findAll().stream()
                .filter(groupSub -> !isEmpty(groupSub.getUsers()))
                .map(groupSub -> new GroupStatDto(groupSub.getId(), groupSub.getTitle(), groupSub.getUsers().size()))
                .collect(Collectors.toList());
        List<TelegramUser> activeUsers = telegramUserService.findAllActiveUsers();
        List<TelegramUser> inActiveUsers = telegramUserService.findAllInActiveUsers();

        double groupsPerUser = getGroupsPerUser(activeUsers);

        return new StatisticDto(activeUsers.size(), inActiveUsers.size(), groupStats, groupsPerUser);
    }

    private double getGroupsPerUser(List<TelegramUser> activeUsers) {
        return (double) activeUsers.stream().mapToInt(user -> user.getGroupSubs().size()).sum() / activeUsers.size();
    }
}
