package com.github.space125.jrtb.jrclient.dto;

import lombok.Data;

/**
 * Group information related to authorized user. If there is no user - will be null.
 *
 * @author Ivan Kurilov on 29.07.2021
 */
@Data
public class MeGroupInfo {

    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
