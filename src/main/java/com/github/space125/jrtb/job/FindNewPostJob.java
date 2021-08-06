package com.github.space125.jrtb.job;

import com.github.space125.jrtb.service.FindNewPostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Job for finding new posts.
 *
 * @author Ivan Kurilov on 04.08.2021
 */
@Slf4j
@Component
@AllArgsConstructor
public class FindNewPostJob {

    private final FindNewPostService findNewPostService;

    @Scheduled(fixedRateString = "${bot.recount-new-post}")
    public void findNewPosts() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new post job started.");

        findNewPostService.findNewPosts();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new post job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
