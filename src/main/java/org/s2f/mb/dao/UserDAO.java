package org.s2f.mb.dao;

import org.s2f.mb.model.entity.User;
import org.s2f.mb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO {

    User findByLogin(String login);

    User findByUuid(String uuid);

    User save(User user);

    boolean existsByLogin(String login);
}