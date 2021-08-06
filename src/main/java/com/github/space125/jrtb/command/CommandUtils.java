package com.github.space125.jrtb.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Utils class for Commands.
 *
 * @author Ivan Kurilov on 02.08.2021
 */
public class CommandUtils {

    /**
     * Find chat ID from {@link Update} object.
     *
     * @param update provided {@link Update}.
     * @return from the provided {@link Update} object.
     */
    public static Long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    public static String getMessage(Update update) {
        return update.getMessage().getText();
    }
}
