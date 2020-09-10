package org.s2f.mb.controllers;

import lombok.extern.slf4j.Slf4j;
import org.s2f.mb.model.entity.User;

import org.s2f.mb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getUserUuid(@RequestBody User user, HttpServletResponse response) {
        log.debug("Authorization: {}", user.getLogin());

        Cookie cookie;
        User userDb;

        String login = user.getLogin();

        if (login == null) {
            log.error("Login is null.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        try {
            userDb = userService.findByLogin(login);
            cookie = new Cookie("uuid", userDb.getUuid());
            log.debug("User exists - {}", userDb.toString());
        } catch (NullPointerException e) {
            log.info("New user.");
            userDb = new User(login);
            userDb.generateUuid();
            userService.save(userDb);
            cookie = new Cookie("uuid", userDb.getUuid());
            log.debug("New user - {}", userDb.toString());
        }

        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);

        return userDb.getUuid();
    }
}