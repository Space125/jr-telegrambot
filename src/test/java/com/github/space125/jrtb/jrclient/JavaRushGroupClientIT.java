package com.github.space125.jrtb.jrclient;

import com.github.space125.jrtb.jrclient.dto.GroupDiscussionInfo;
import com.github.space125.jrtb.jrclient.dto.GroupInfo;
import com.github.space125.jrtb.jrclient.dto.GroupRequestArgs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.space125.jrtb.jrclient.dto.GroupInfoType.TECH;

/**
 * Integration-level testing for {@link JavaRushGroupClientImpl}
 *
 * @author Ivan Kurilov on 29.07.2021
 */
@DisplayName("Integration-level testing for JavaRushGroupClientImpl")
class JavaRushGroupClientIT {

    public static final String JAVARUSH_API_PATH = "https://javarush.ru/api/1.0/rest";

    private final JavaRushGroupClient groupClient = new JavaRushGroupClientImpl(JAVARUSH_API_PATH);

    @Test
    void shouldProperlyGetGroupsWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupInfo> groups = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groups);
        Assertions.assertFalse(groups.isEmpty());
    }

    @Test
    void shouldProperlyGetWithOffsetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupInfo> groups = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groups);
        Assertions.assertEquals(3, groups.size());
    }

    @Test
    void shouldProperlyGetGroupDiscussionsWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupDiscussionInfo> groups = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groups);
        Assertions.assertFalse(groups.isEmpty());
    }

    @Test
    void shouldProperlyGetGroupDiscussionsWithOffsetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupDiscussionInfo> groups = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groups);
        Assertions.assertEquals(3, groups.size());
    }

    @Test
    void shouldProperlyGetGroupCount() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(30, groupCount);
    }

    @Test
    void shouldProperlyGetGroupTECHCount() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .type(TECH)
                .build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(7, groupCount);
    }

    @Test
    void shouldProperlyGetGroupById() {
        //given
        Integer androidGroupId = 16;

        //when
        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        //then
        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(TECH, groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }
}