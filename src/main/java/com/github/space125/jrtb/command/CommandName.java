package com.github.space125.jrtb.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration for {@link Command}'s.
 *
 * @author Ivan Kurilov on 09.07.2021
 */

@AllArgsConstructor
public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    STAT("/stat"),
    ADD_GROUP_SUB("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub"),
    NO("no command");

    @Getter
    private final String commandName;

}
