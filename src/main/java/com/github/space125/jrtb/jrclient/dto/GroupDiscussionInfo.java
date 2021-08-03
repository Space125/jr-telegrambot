package com.github.space125.jrtb.jrclient.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTO Group discussion info class.
 *
 * @author Ivan Kurilov on 29.07.2021
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupDiscussionInfo extends GroupInfo {
    private Integer commentsCount;
    private UserDiscussionInfo userDiscussionInfo;
}
