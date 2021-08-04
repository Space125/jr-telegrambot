package com.github.space125.jrtb.service;

import com.github.space125.jrtb.jrclient.JavaRushPostClient;
import com.github.space125.jrtb.jrclient.dto.PostInfo;
import com.github.space125.jrtb.repository.entity.GroupSub;
import com.github.space125.jrtb.repository.entity.TelegramUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Kurilov on 04.08.2021
 */
@Service
@AllArgsConstructor
public class FindNewArticleServiceImpl implements FindNewArticleService {

    private final static String JAVARUSH_WEB_POST_FORMAT = "https://javarush.ru/groups/posts/%s";

    private final GroupSubService groupSubService;
    private final JavaRushPostClient javaRushPostClient;
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void findNewArticles() {
        groupSubService.findAll().forEach(groupSub -> {
            List<PostInfo> newPosts = javaRushPostClient.findNewPosts(groupSub.getId(), groupSub.getLastArticleId());

            setNewLastArticleId(groupSub, newPosts);

            notifySubscribersNewArticles(groupSub, newPosts);
        });
    }

    private void notifySubscribersNewArticles(GroupSub groupSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messagesWithNewArticle = newPosts.stream()
                .map(post -> String.format("✨Вышла новая статья <b>%s</b> в группе <b>%s</b>.✨\n\n" +
                                "<b>Описание:</b> %s\n\n" +
                                "<b>Ссылка:</b> %s\n",
                        post.getTitle(), groupSub.getTitle(), post.getDescription(), getPostUrl(post.getKey())))
                .collect(Collectors.toList());

        groupSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(user -> sendBotMessageService.sendMessage(user.getChatId(), messagesWithNewArticle));
    }

    private String getPostUrl(String key) {
        return String.format(JAVARUSH_WEB_POST_FORMAT, key);
    }

    private void setNewLastArticleId(GroupSub groupSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id -> {
                    groupSub.setLastArticleId(id);
                    groupSubService.save(groupSub);
                });
    }
}
