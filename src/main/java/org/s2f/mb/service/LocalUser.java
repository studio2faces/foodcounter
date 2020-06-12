package org.s2f.mb.service;

import org.s2f.mb.model.dto.User;

public class LocalUser {
    private static ThreadLocal<User> LOCAL_USER = new ThreadLocal<>();

    public static ThreadLocal<User> getLocalUser() {
        return LOCAL_USER;
    }
}
