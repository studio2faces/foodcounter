package org.s2f.mb.service;

import lombok.extern.slf4j.Slf4j;
import org.s2f.mb.dao.UserDAO;
import org.s2f.mb.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserService() {
    }

    public User findByLogin(String login) {
        if (userDAO.existsByLogin(login)) {
            return userDAO.findByLogin(login);
        } else {
            log.debug("No users in DB with login {}.", login);
            throw new NullPointerException();
        }
    }

    public User getUserByUuid(String uuid) {
        return userDAO.findByUuid(uuid);
    }

    public void save(User user) {
        userDAO.save(user);
    }
}
