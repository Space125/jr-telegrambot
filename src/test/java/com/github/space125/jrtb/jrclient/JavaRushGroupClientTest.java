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
class JavaRushGroupClientTest {
    private final JavaRushGroupClient groupClient = new JavaRushGroupClientImpl("https://javarush.ru/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupsWithEmptyArgs(){
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupInfo> groups = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groups);
        Assertions.assertFalse(groups.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffsetAndLimit(){
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
    public void shouldProperlyGetGroupDiscussionsWithEmptyArgs(){
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupDiscussionInfo> groups = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groups);
        Assertions.assertFalse(groups.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupDiscussionsWithOffsetAndLimit(){
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
    public void shouldProperlyGetGroupCount(){
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(30, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount(){
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
    public void shouldProperlyGetGroupById(){
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