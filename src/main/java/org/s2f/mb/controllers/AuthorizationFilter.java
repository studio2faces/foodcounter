package org.s2f.mb.controllers;

import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends HttpFilter {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);
    private final DatabaseHandler databaseHandler;

    @Autowired
    public AuthorizationFilter(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String uuid = null;

        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("uuid")) {
                    uuid = cookie.getValue();
                }
            }
            System.out.println(uuid);

            if (uuid != null) {
                try {
                    User loggedUser = databaseHandler.getUserByUuid(uuid);
                    log.debug("Authorized {}", loggedUser.toString());
                    LocalUser.setLoggedUser(loggedUser);

                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    log.debug("Incorrect uuid. No user in DB with uuid {}", uuid);
                }
            }
        } catch (Exception e) {
            log.error("Uuid is null.");
        }
    }

}