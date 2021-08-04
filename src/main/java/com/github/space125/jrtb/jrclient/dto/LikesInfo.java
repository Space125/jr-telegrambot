package com.github.space125.jrtb.jrclient.dto;

import lombok.Data;

/**
 * DTO, which represents likes info.
 *
 * @author Ivan Kurilov on 03.08.2021
 */
@Data
public class LikesInfo {

    private Integer count;
    private LikesInfoStatus status;
}
