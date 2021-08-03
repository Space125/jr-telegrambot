package com.github.space125.jrtb.command;

import com.github.space125.jrtb.jrclient.JavaRushGroupClient;
import com.github.space125.jrtb.jrclient.dto.GroupDiscussionInfo;
import com.github.space125.jrtb.jrclient.dto.GroupRequestArgs;
import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.service.GroupSubService;
import com.github.space125.jrtb.service.SendBotMessageService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.github.space125.jrtb.command.CommandName.ADD_GROUP_SUB;
import static com.github.space125.jrtb.command.CommandUtils.getChatId;
import static com.github.space125.jrtb.command.CommandUtils.getMessage;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Command Add Group Subscription {@link Command}
 *
 * @author Ivan Kurilov on 02.08.2021
 */
@AllArgsConstructor
public class AddGroupSubCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final JavaRushGroupClient javaRushGroupClient;
    private final GroupSubService groupSubService;

    @Override
    public void execute(Update update) {
        if(getMessage(update).equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())){
            sendGroupIdList(getChatId(update));
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = getChatId(update);
        if(isNumeric(groupId)){
            GroupDiscussionInfo groupById = javaRushGroupClient.getGroupById(Integer.parseInt(groupId));
            if(isNull(groupById.getId())){
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, String.format("Вы подписаны на группу - %s", savedGroupSub.getTitle()));
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }

    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(String chatId) {
        String groupId = javaRushGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "Чтобы подписаться на группу - передай комадну вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "я подготовил список всех групп - выбирай какую хочешь :) \n\n" +
                "имя группы - ID группы \n\n" +
                "%s";
        sendBotMessageService.sendMessage(chatId, String.format(message, groupId));
    }
}
