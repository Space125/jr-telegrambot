package com.github.space125.jrtb.jrclient;

import com.github.space125.jrtb.jrclient.dto.PostInfo;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link JavaRushPostClient} interface.
 *
 * @author Ivan Kurilov on 03.08.2021
 */
@Component
public class JavaRushPostClientImpl implements JavaRushPostClient {

    private final String javarushApiPostPath;

    public JavaRushPostClientImpl(@Value("${javarush.api.path}") String javarushApiPostPath) {
        this.javarushApiPostPath = javarushApiPostPath + "/posts";
    }

    @Override
    public List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId) {
        List<PostInfo> lastPostsByGroup = Unirest.get(javarushApiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupId)
                .queryString("limit", 15)
                .asObject(new GenericType<List<PostInfo>>() {
                }).getBody();

        List<PostInfo> newPosts = new ArrayList<>();
        for (PostInfo postInfo : lastPostsByGroup) {
            if (lastPostId.equals(postInfo.getId())) {
                return newPosts;
            }
            newPosts.add(postInfo);
        }

        return newPosts;
    }
}
