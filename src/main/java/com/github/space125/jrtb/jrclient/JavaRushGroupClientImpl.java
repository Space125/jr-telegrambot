package com.github.space125.jrtb.jrclient;

import com.github.space125.jrtb.jrclient.dto.GroupDiscussionInfo;
import com.github.space125.jrtb.jrclient.dto.GroupInfo;
import com.github.space125.jrtb.jrclient.dto.GroupRequestArgs;
import com.github.space125.jrtb.jrclient.dto.PostInfo;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Implementation of the {@link JavaRushGroupClient} interface.
 *
 * @author Ivan Kurilov on 29.07.2021
 */
@Component
public class JavaRushGroupClientImpl implements JavaRushGroupClient {

    private final String javarushApiGroupPath;
    private final String javarushApiPostPath;

    public JavaRushGroupClientImpl(@Value("${javarush.api.path}") String javarushApiPath) {
        this.javarushApiGroupPath = javarushApiPath + "/groups";
        this.javarushApiPostPath = javarushApiPath + "/posts";
    }

    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs args) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(args.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                })
                .getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs args) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(args.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                })
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupRequestArgs args) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", javarushApiGroupPath))
                        .queryString(args.populateQueries())
                        .asString()
                        .getBody()
        );
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", javarushApiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }

    @Override
    public Integer findLastPostId(Integer groupSubId) {
        List<PostInfo> posts = Unirest.get(javarushApiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupSubId.toString())
                .queryString("limit", 1)
                .asObject(new GenericType<List<PostInfo>>() {
                }).getBody();
        return isEmpty(posts) ? 0 : Optional.ofNullable(posts.get(0)).map(PostInfo::getId).orElse(0);
    }
}
