package com.github.space125.jrtb.service;

import com.github.space125.jrtb.jrclient.dto.GroupDiscussionInfo;
import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;

/**
 * Service for manipulating with {@link GroupSub}
 *
 * @author Ivan Kurilov on 02.08.2021
 */
public interface GroupSubService {
    /**
     * Save provided {@link GroupSub} entity.
     *
     * @param chatId provided chat ID from {@link TelegramUser}
     * @param groupDiscussionInfo provided {@link GroupDiscussionInfo}
     * @return {@link GroupSub} with provided chat ID
     */
    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
