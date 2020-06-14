package org.s2f.mb.model.dto;

import java.util.UUID;

public class User {
    private String login;
    private UUID uuid;

    public User(String login, UUID uuid) {
        this.login = login;
        this.uuid = uuid;
    }

    public User(String login) {
        this.login = login;
        uuid = UUID.randomUUID();
    }

    public void generateUuid() {
        this.uuid = UUID.randomUUID();
    }

    public void generateUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLogin() {
        return login;
    }

    public UUID getUuid() {
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