package com.github.space125.jrtb.jrclient;

import com.github.space125.jrtb.jrclient.dto.GroupDiscussionInfo;
import com.github.space125.jrtb.jrclient.dto.GroupInfo;
import com.github.space125.jrtb.jrclient.dto.GroupRequestArgs;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of the {@link JavaRushGroupClient} interface.
 *
 * @author Ivan Kurilov on 29.07.2021
 */
@Component
public class JavaRushGroupClientImpl implements JavaRushGroupClient {

    private final String javarushApiGroupPath;

    public JavaRushGroupClientImpl(@Value("${javarush.api.path}") String javarushApiGroupPath) {
        this.javarushApiGroupPath = javarushApiGroupPath + "/groups";
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
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {})
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupRequestArgs args) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count",javarushApiGroupPath))
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
}
