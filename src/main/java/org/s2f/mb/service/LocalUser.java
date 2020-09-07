package org.s2f.mb.service;

import org.s2f.mb.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalUser {
    private static final Logger log = LoggerFactory.getLogger(LocalUser.class);

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