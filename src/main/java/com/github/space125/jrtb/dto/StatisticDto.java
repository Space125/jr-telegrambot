package com.github.space125.jrtb.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * DTO for getting bot statistics.
 *
 * @author Ivan Kurilov on 05.08.2021
 */
@Data
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticDto {
    int activeUserCount;
    int inactiveUserCount;
    List<GroupStatDto> groupStats;
    double averageGroupCountByUser;
}
