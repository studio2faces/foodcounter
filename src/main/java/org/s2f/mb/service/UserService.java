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
