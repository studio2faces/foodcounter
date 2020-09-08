package org.s2f.mb.service;

import org.s2f.mb.dao.UserDAO;
import org.s2f.mb.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public UserService() {
    }

    public User findByLogin(String login) {
        User user = null;
        if (userDAO.existsByLogin(login)) {
            user = userDAO.findByLogin(login);
        } else return new User(login);
        return user;
    }

    public User getUserByUuid(String uuid) {
        return userDAO.findByUuid(uuid);
    }

    public void save(User user) {
        userDAO.save(user);
    }
}
