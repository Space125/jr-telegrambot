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
    NO("no command");

    @Getter
    private final String commandName;

}
