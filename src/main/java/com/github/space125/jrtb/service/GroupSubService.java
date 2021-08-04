package com.github.space125.jrtb.service;

import com.github.space125.jrtb.jrclient.dto.GroupDiscussionInfo;
import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

/**
 * Service for manipulating with {@link GroupSub}
 *
 * @author Ivan Kurilov on 02.08.2021
 */
public interface GroupSubService {

    /**
     * Save provided {@link GroupSub} entity.
     *
     * @param chatId              provided chat ID from {@link TelegramUser}
     * @param groupDiscussionInfo provided {@link GroupDiscussionInfo}
     * @return {@link GroupSub} with provided chat ID
     */
    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);

    /**
     * Save provided {@link GroupSub} entity.
     *
     * @param groupSub provided {@link GroupSub}.
     * @return {@link GroupSub}.
     */
    GroupSub save(GroupSub groupSub);

    /**
     * Find by ID {@link GroupSub}
     *
     * @param groupId provided group ID.
     * @return {@link Optional} {@link GroupSub} with provided groupID.
     */
    Optional<GroupSub> findById(Integer groupId);

    /**
     * Find all {@link GroupSub}
     *
     * @return the list all {@link GroupSub}
     */
    List<GroupSub> findAll();
}
