package com.github.space125.jrtb.jrclient.dto;

import lombok.Data;
import lombok.ToString;

/**
 * DTO for Group Info.
 *
 * @author Ivan Kurilov on 29.07.2021
 */
@Data
@ToString
public class GroupInfo {
    private Integer id;
    private String avatarUrl;
    private String createTime;
    private String description;
    private String key;
    private Integer levelToEditor;
    private MeGroupInfo meGroupInfo;
    private String pictureUrl;
    private String title;
    private GroupInfoType type;
    private Integer userCount;
    private GroupInfoVisibilityStatus visibilityStatus;
}
