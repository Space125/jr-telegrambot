package com.github.space125.jrtb.command;

import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import com.github.space125.jrtb.service.GroupSubService;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.space125.jrtb.command.CommandName.DEL_GROUP_SUB;
import static com.github.space125.jrtb.command.CommandUtils.getChatId;
import static com.github.space125.jrtb.command.CommandUtils.getMessage;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.springframework.util.CollectionUtils.isEmpty;


/**
 * Command Delete Group Subscription {@link Command}
 *
 * @author Ivan Kurilov on 03.08.2021
 */
@AllArgsConstructor
public class DelGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final GroupSubService groupSubService;
    private final TelegramUserService telegramUserService;

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(DEL_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }
        Long chatId = getChatId(update);
        String groupId = getMessage(update).split(SPACE)[1];
        if (isNumeric(groupId)) {
            Optional<GroupSub> groupSubFromDb = groupSubService.findById(Integer.parseInt(groupId));
            if (groupSubFromDb.isPresent()) {
                GroupSub groupSub = groupSubFromDb.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(chatId, String.format("Вы удалили подписку на группу - %s", groupSub.getTitle()));
            } else {
                sendBotMessageService.sendMessage(chatId, "Среди ваших подписок, нет группы с указанным ID!");
            }
        } else {
            sendBotMessageService.sendMessage(chatId, "Неверный формат ID группы.\n"
                    + "ID группы должно быть целым положительным числом");
        }


    }

    private void sendGroupIdList(Long chatId) {
        List<GroupSub> groupSubs = telegramUserService.findByChatId(chatId)
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        String message;
        if (isEmpty(groupSubs)) {
            message = "У вас пока нет подписок, чтобы подписаться введите команду /addgroupsub";
        } else {
            String userGroupSub = groupSubs.stream()
                    .map(group -> String.format("%s - %s\n", group.getTitle(), group.getId()))
                    .collect(Collectors.joining());

            message = String.format("Чтобы удалить подписку на группу - передай команду вместе с ID группы. \n" +
                    "Например: /delgroupsub 16 \n\n" +
                    "я подготовил список всех групп, на которые ты подписан) \n\n" +
                    "имя группы - ID группы: \n" +
                    "%s", userGroupSub);
        }
        sendBotMessageService.sendMessage(chatId, message);
    }
}
