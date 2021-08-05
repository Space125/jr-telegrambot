package com.github.space125.jrtb.command.annotation;

import com.github.space125.jrtb.command.Command;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Indicates that an annotated {@link Command} can be viewed only by admins.
 *
 * @author Ivan Kurilov on 05.08.2021
 */
@Retention(RUNTIME)
public @interface AdminCommand {
}
