package com.github.space125.jrtb.service;

/**
 * Service for finding new postss.
 *
 * @author Ivan Kurilov on 04.08.2021
 */
public interface FindNewPostService {

    /**
     * Find new posts and notify subscribers about it.
     */
    void findNewPosts();
}
