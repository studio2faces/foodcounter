package org.s2f.mb.controllers;

import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.db.DatabaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final DatabaseHandler databaseHandler;

    @Autowired
    public AuthController(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @PostMapping(path = "/getUsersUuid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserUuid(@RequestBody User user, HttpServletResponse response) {

        user.setUuid(databaseHandler.getUuidByLogin(user.getLogin()));
        log.debug("Authorization: {}", user.toString());
        Cookie cookie = null;

        if (user.getUuid() == null) {
            log.info("New user.");
            user.generateUuid();
            databaseHandler.addUser(user);

            cookie = new Cookie("uuid", user.getUuid());
            log.debug("New user - {}", user.toString());
        } else {
            cookie = new Cookie("uuid", user.getUuid());
            log.debug("User exists - {}", user.toString());
        }

        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);

        return user.getUuid();
    }
}