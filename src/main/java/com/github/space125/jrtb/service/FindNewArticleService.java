package com.github.space125.jrtb.service;

/**
 * Service for finding new articles.
 *
 * @author Ivan Kurilov on 04.08.2021
 */
public interface FindNewArticleService {

    /**
     * Find new articles and notify subscribers about it.
     */
    void findNewArticles();
}
