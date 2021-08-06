package com.github.space125.jrtb.repository;

import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Integration-level testing for {@link TelegramUserRepository}.
 *
 * @author Ivan Kurilov on 28.07.2021
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@DisplayName("Integration-level testing for TelegramUserRepository")
class TelegramUserRepositoryIT {

    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegramUsers.sql"})
    @Test
    void shouldProperlyFindAllActiveUsers() {
        //when
        List<TelegramUser> telegramUsers = telegramUserRepository.findAllByActiveTrue();

        //then
        Assertions.assertEquals(5, telegramUsers.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    void shouldProperlySaveTelegramUser() {
        //given
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId(1234567890L);
        telegramUser.setActive(true);
        telegramUserRepository.save(telegramUser);

        //when
        Optional<TelegramUser> savedUser = telegramUserRepository.findById(telegramUser.getChatId());

        //then
        Assertions.assertTrue(savedUser.isPresent());
        Assertions.assertEquals(telegramUser, savedUser.get());
    }

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/groupSubsForUser.sql"})
    @Test
    void shouldProperlyGetAllGroupSubsForUser() {
        //when
        Optional<TelegramUser> userFromDb = telegramUserRepository.findById(1L);

        //then
        Assertions.assertTrue(userFromDb.isPresent());
        List<GroupSub> groupSubs = userFromDb.get().getGroupSubs();
        for (int i = 0; i < groupSubs.size(); i++) {
            Assertions.assertEquals(i + 1, groupSubs.get(i).getId());
            Assertions.assertEquals(i + 1, groupSubs.get(i).getLastPostId());
            Assertions.assertEquals(String.format("g%s", i + 1), groupSubs.get(i).getTitle());
        }
    }
}
