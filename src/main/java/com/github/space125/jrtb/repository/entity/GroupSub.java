package com.github.space125.jrtb.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ivan Kurilov on 02.08.2021
 */
@Data
@EqualsAndHashCode(exclude = "users")
@Entity
@Table(name = "group_sub")
@ToString
public class GroupSub {

    @Id
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "last_post_id")
    private Integer lastPostId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_x_user",
            joinColumns = @JoinColumn(name = "group_sub_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<TelegramUser> users;

    public void addUser(TelegramUser telegramUser) {
        if (Objects.isNull(users)) {
            users = new ArrayList<>();
        }
        users.add(telegramUser);
    }
}
