package com.github.space125.jrtb.service;

import java.util.List;

/**
 * Service for sending messages via telegram-bot.
 *
 * @author Ivan Kurilov on 09.07.2021
 */
public interface SendBotMessageService {

    /**
     * Send message via telegram bot.
     *
     * @param chatId  provided chatId in which messages would be sent.
     * @param message provided message to be sent.
     */
    void sendMessage(String chatId, String message);

    /**
     * Send message with new articles via telegram bot.
     *
     * @param chatId   provided chatId in which messages would be sent.
     * @param messages provided new articles
     */
    void sendMessage(String chatId, List<String> messages);
}
