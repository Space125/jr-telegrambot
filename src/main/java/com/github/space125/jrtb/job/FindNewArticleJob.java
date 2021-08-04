package com.github.space125.jrtb.job;

import com.github.space125.jrtb.service.FindNewArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Job for finding new articles.
 *
 * @author Ivan Kurilov on 04.08.2021
 */
@Slf4j
@Component
@AllArgsConstructor
public class FindNewArticleJob {

    private final FindNewArticleService findNewArticleService;

    @Scheduled(fixedRateString = "${bot.recount-new-article}")
    public void findNewArticles() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new article job started.");

        findNewArticleService.findNewArticles();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new article job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
