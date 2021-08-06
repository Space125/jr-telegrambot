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
 * Integration-level testing for {@link GroupSubRepository}.
 *
 * @author Ivan Kurilov on 02.08.2021
 */
@ActiveProfiles("test")
@DisplayName("Integration-level testing for GroupSubRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class GroupSubRepositoryIT {

    @Autowired
    private GroupSubRepository groupSubRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/usersForGroupSub.sql"})
    @Test
    void shouldProperlyGetAllUsersForGroupSub() {
        //when
        Optional<GroupSub> groupSubFromDb = groupSubRepository.findById(1);

        //then
        Assertions.assertTrue(groupSubFromDb.isPresent());
        Assertions.assertEquals(1, groupSubFromDb.get().getId());
        List<TelegramUser> users = groupSubFromDb.get().getUsers();
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(Long.valueOf(i + 1), users.get(i).getChatId());
            Assertions.assertTrue(users.get(i).isActive());
        }
    }
}
