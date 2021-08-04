package com.github.space125.jrtb.jrclient.dto;

import lombok.Data;

/**
 * DTO, which represents Base User Info.
 *
 * @author Ivan Kurilov on 03.08.2021
 */
@Data
public class BaseUserInfo {

    private String city;
    private String country;
    private String displayName;
    private Integer id;
    private String job;
    private String key;
    private Integer level;
    private String pictureUrl;
    private String position;
    private UserPublicStatus publicStatus;
    private String publicStatusMessage;
    private Integer rating;
    private Integer userId;
}
