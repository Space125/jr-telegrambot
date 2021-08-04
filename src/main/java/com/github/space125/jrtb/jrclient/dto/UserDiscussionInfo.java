package com.github.space125.jrtb.jrclient.dto;

import lombok.Data;

/**
 * DTO for User discussion info.
 *
 * @author Ivan Kurilov on 29.07.2021
 */
@Data
public class UserDiscussionInfo {

    private Boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;
}
