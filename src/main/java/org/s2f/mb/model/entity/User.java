package org.s2f.mb.model.entity;

import org.hibernate.annotations.Proxy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
@Transactional
public class User implements Serializable {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "uuid")
    private String uuid;

    public User() {
    }

    public User(String login, String uuid) {
        this.login = login;
        this.uuid = uuid;
    }

    public User(String login) {
        this.login = login;
        uuid = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void generateUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}