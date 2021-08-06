package com.github.space125.jrtb.command;

import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import com.github.space125.jrtb.service.GroupSubService;
import com.github.space125.jrtb.service.SendBotMessageService;
import com.github.space125.jrtb.service.TelegramUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Optional;

import static com.github.space125.jrtb.command.AbstractCommandTest.prepareUpdate;
import static com.github.space125.jrtb.command.CommandName.DEL_GROUP_SUB;
import static java.util.Collections.singletonList;

/**
 * @author Ivan Kurilov on 03.08.2021
 */
@DisplayName("Unit-level testing for DelGroupSubCommand")
class DelGroupSubCommandTest {

    private Command command;
    private SendBotMessageService sendBotMessageService;
    private GroupSubService groupSubService;
    private TelegramUserService telegramUserService;


    @BeforeEach
    void init() {
        sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        groupSubService = Mockito.mock(GroupSubService.class);
        telegramUserService = Mockito.mock(TelegramUserService.class);

        command = new DelGroupSubCommand(sendBotMessageService, groupSubService, telegramUserService);
    }

    @Test
    void shouldProperlyReturnEmptySubscriptionList() {
        //given
        Long chatId = 23456L;
        Update update = prepareUpdate(chatId, DEL_GROUP_SUB.getCommandName());

        Mockito.when(telegramUserService.findByChatId(chatId))
                .thenReturn(Optional.of(new TelegramUser()));

        String expectedMessage = "У вас пока нет подписок, чтобы подписаться введите команду /addgroupsub";

        //when
        command.execute(update);

        //then
        Mockito.verify(sendBotMessageService).sendMessage(chatId, expectedMessage);
    }

    @Test
    void shouldProperlyReturnSubscriptionList() {
        //given
        Long chatId = 23456L;
        Update update = prepareUpdate(chatId, DEL_GROUP_SUB.getCommandName());
        TelegramUser telegramUser = new TelegramUser();
        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        telegramUser.setGroupSubs(singletonList(gs1));
        Mockito.when(telegramUserService.findByChatId(chatId))
                .thenReturn(Optional.of(telegramUser));

        String expectedMessage = "Чтобы удалить подписку на группу - передай команду вместе с ID группы. \n" +
                "Например: /delgroupsub 16 \n\n" +
                "я подготовил список всех групп, на которые ты подписан) \n\n" +
                "имя группы - ID группы: \n" +
                "GS1 Title - 123\n";

        //when
        command.execute(update);

        //then
        Mockito.verify(sendBotMessageService).sendMessage(chatId, expectedMessage);
    }

    @Test
    void shouldRejectByInvalidGroupId() {
        //given
        Long chatId = 23456L;
        Update update = prepareUpdate(chatId, String.format("%s %s", DEL_GROUP_SUB.getCommandName(), "groupSubId"));
        TelegramUser telegramUser = new TelegramUser();
        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        telegramUser.setGroupSubs(singletonList(gs1));
        Mockito.when(telegramUserService.findByChatId(chatId))
                .thenReturn(Optional.of(telegramUser));

        String expectedMessage = "Неверный формат ID группы.\n" +
                "ID группы должно быть целым положительным числом";

        //when
        command.execute(update);

        //then
        Mockito.verify(sendBotMessageService).sendMessage(chatId, expectedMessage);
    }

    @Test
    void shouldProperlyDeleteByGroupId() {
        //given

        /// prepare update object
        Long chatId = 23456L;
        Integer groupId = 1234;
        Update update = prepareUpdate(chatId, String.format("%s %s", DEL_GROUP_SUB.getCommandName(), groupId));


        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId(chatId);
        telegramUser.setGroupSubs(singletonList(gs1));
        ArrayList<TelegramUser> users = new ArrayList<>();
        users.add(telegramUser);
        gs1.setUsers(users);
        Mockito.when(groupSubService.findById(groupId)).thenReturn(Optional.of(gs1));
        Mockito.when(telegramUserService.findByChatId(chatId))
                .thenReturn(Optional.of(telegramUser));

        String expectedMessage = "Вы удалили подписку на группу - GS1 Title";

        //when
        command.execute(update);

        //then
        users.remove(telegramUser);
        Mockito.verify(groupSubService).save(gs1);
        Mockito.verify(sendBotMessageService).sendMessage(chatId, expectedMessage);
    }

    @Test
    void shouldDoesNotExistByGroupId() {
        //given
        Long chatId = 23456L;
        Integer groupId = 1234;
        Update update = prepareUpdate(chatId, String.format("%s %s", DEL_GROUP_SUB.getCommandName(), groupId));


        Mockito.when(groupSubService.findById(groupId)).thenReturn(Optional.empty());

        String expectedMessage = "Среди ваших подписок, нет группы с указанным ID!";

        //when
        command.execute(update);

        //then
        Mockito.verify(groupSubService).findById(groupId);
        Mockito.verify(sendBotMessageService).sendMessage(chatId, expectedMessage);
    }
}