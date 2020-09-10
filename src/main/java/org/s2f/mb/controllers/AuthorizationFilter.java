package org.s2f.mb.controllers;

import lombok.extern.slf4j.Slf4j;
import org.s2f.mb.model.entity.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthorizationFilter extends HttpFilter {

    @Autowired
    public UserService userService;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String uuid = null;

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            log.error("Request without cookies. Not authorized user.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not authorized user.");
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("uuid")) {
                uuid = cookie.getValue();
            }
        }
        if (uuid == null) {
            log.warn("Uuid is null.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is null.");
        } else {
            try {
                User loggedUser = userService.getUserByUuid(uuid);
                log.debug("Authorized {}", loggedUser.toString());
                LocalUser.setLoggedUser(loggedUser);

                filterChain.doFilter(request, response);
            } catch (Exception e) {
                log.debug("Incorrect uuid. No user in DB with uuid {}", uuid);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect uuid.");
            }
        }
    }
}