package com.github.space125.jrtb.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Telegram User entity.
 *
 * @author Ivan Kurilov on 28.07.2021
 */
@Data
@Entity
@Table(name = "tg_user")
@EqualsAndHashCode(exclude = "groupSubs")
@ToString
public class TelegramUser {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<GroupSub> groupSubs;
}
