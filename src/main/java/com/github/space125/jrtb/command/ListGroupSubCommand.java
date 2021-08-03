package com.github.space125.jrtb.command;

import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.ws.rs.NotFoundException;
import java.util.stream.Collectors;

import static com.github.space125.jrtb.command.CommandUtils.getChatId;

/**
 * {@link Command} for getting list of {@link GroupSub}
 * @author Ivan Kurilov on 03.08.2021
 */
@AllArgsConstructor
public class ListGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    @Override
    public void execute(Update update) {
        // TODO add exception handling
        TelegramUser telegramUser = telegramUserService.findByChatId(getChatId(update))
                .orElseThrow(NotFoundException::new);
        String message;
        if(telegramUser.getGroupSubs().isEmpty()){
            message = "Пока нет подписок на группы. Чтобы добавить подписку используй команду /addgroupsub";
        } else {
            String collectedGroup = telegramUser.getGroupSubs().stream()
                    .map(group -> String.format("Группа %s, ID = %s\n", group.getTitle(), group.getId()))
                    .collect(Collectors.joining());
            message = String.format("Я нашел все подписки на группы:\n\n%s", collectedGroup);
        }

        sendBotMessageService.sendMessage(telegramUser.getChatId(), message);
    }
}
