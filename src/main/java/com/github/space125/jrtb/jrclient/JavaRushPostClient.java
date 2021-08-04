package com.github.space125.jrtb.jrclient;

import com.github.space125.jrtb.jrclient.dto.PostInfo;

import java.util.List;

/**
 * Client for Javarush Open API corresponds to Posts.
 *
 * @author Ivan Kurilov on 03.08.2021
 */
public interface JavaRushPostClient {

    /**
     * Find new posts since lastPostId in provided group.
     *
     * @param groupId    provided group ID.
     * @param lastPostId provided last post ID.
     * @return the list of the new {@link PostInfo}.
     */
    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);

}
