package com.github.space125.jrtb.service;

import com.github.space125.jrtb.dto.StatisticDto;

/**
 * Service for getting bot statistic.
 *
 * @author Ivan Kurilov on 05.08.2021
 */
public interface StatisticService {

    /**
     * Getting bot statistic.
     *
     * @return {@link StatisticDto} object.
     */
    StatisticDto countBotStatistic();
}
