package org.s2f.mb.dao;

import org.s2f.mb.model.entity.User;

public interface UserDAO {

    User findByLogin(String login);

    User findByUuid(String uuid);

    User save(User user);

    boolean existsByLogin(String login);
}
