package org.s2f.mb.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name = "login")
    @NonNull
    private String login;

    @Column(name = "uuid")
    private String uuid;

    public void generateUuid() {
        this.uuid = UUID.randomUUID().toString();
    }
}