package com.github.space125.jrtb.jrclient;

import com.github.space125.jrtb.jrclient.dto.GroupDiscussionInfo;
import com.github.space125.jrtb.jrclient.dto.GroupInfo;
import com.github.space125.jrtb.jrclient.dto.GroupRequestArgs;

import java.util.List;

/**
 * Client for JavaRush Open API corresponds to Groups.
 *
 * @author Ivan Kurilov on 29.07.2021
 */
public interface JavaRushGroupClient {

    /**
     * Get all the {@link GroupInfo} filtered by provided {@link GroupRequestArgs}.
     *
     * @param args provided {@link GroupRequestArgs}.
     * @return the list of the {@link GroupInfo} objects.
     */
    List<GroupInfo> getGroupList(GroupRequestArgs args);

    /**
     * Get all the {@link GroupDiscussionInfo} filtered by provided {@link GroupRequestArgs}.
     *
     * @param args provided {@link GroupRequestArgs}.
     * @return the list of the {@link GroupDiscussionInfo} objects.
     */
    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs args);

    /**
     * Get count of groups filtered by provided {@link GroupRequestArgs}.
     *
     * @param args provided {@link GroupRequestArgs}.
     * @return the count of the group.
     */
    Integer getGroupCount(GroupRequestArgs args);

    /**
     * Get {@link GroupDiscussionInfo} by provided ID.
     *
     * @param id provided ID.
     * @return {@link GroupDiscussionInfo} object.
     */
    GroupDiscussionInfo getGroupById(Integer id);

    /**
     * Find last article ID.
     *
     * @param groupSubId provided group subscription ID.
     * @return last article id.
     */
    Integer findLastArticleId(Integer groupSubId);
}
