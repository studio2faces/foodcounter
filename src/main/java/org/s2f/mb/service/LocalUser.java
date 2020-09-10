package org.s2f.mb.service;

import lombok.extern.slf4j.Slf4j;
import org.s2f.mb.model.entity.User;

@Slf4j
public class LocalUser {

    private final static ThreadLocal<User> LOCAL_USER = new ThreadLocal<>();

    public static User getLoggedUser() {
        log.debug("LocalUser = {}", LOCAL_USER.get().getLogin());
        return LOCAL_USER.get();
    }

    public static void setLoggedUser(User user) {
        LOCAL_USER.set(user);
        log.debug("LocalUser = {}", LOCAL_USER.get().getLogin());
    }
}