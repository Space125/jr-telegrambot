package com.github.space125.jrtb.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

/**
 * DTO for showing group ID and title without data.
 *
 * @author Ivan Kurilov on 05.08.2021
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EqualsAndHashCode(exclude = {"title", "activeUserCount"})
public class GroupStatDto {
    int id;
    String title;
    int activeUserCount;
}
