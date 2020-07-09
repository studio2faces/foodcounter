package org.s2f.mb.model.dto;

import java.util.UUID;

public class User {
    private String login;
    private String uuid;

    public User(String login, String uuid) {
        this.login = login;
        this.uuid = uuid;
    }

    public User(String login) {
        this.login = login;
        uuid = UUID.randomUUID().toString();
    }

    public void generateUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLogin() {
        return login;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}