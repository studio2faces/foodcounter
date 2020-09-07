package org.s2f.mb.controllers;

import org.s2f.mb.model.entity.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationFilter extends HttpFilter {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Autowired
    public UserService userService;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  {
        String uuid = null;

        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uuid")) {
                    uuid = cookie.getValue();
                }
            }
            if (uuid == null) {
                log.debug("Uuid is null.");
            } else {
                try {
                    User loggedUser = userService.getUserByUuid(uuid);
                    log.debug("Authorized {}", loggedUser.toString());
                    LocalUser.setLoggedUser(loggedUser);

                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.debug("Incorrect uuid. No user in DB with uuid {}", uuid);
                }
            }
        } catch (NullPointerException e) {
            log.error("User is null", e);
        }
    }
}