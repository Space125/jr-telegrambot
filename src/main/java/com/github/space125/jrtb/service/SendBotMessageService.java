package com.github.space125.jrtb.service;

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
}
