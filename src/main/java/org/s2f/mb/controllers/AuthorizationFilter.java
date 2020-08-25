package org.s2f.mb.controllers;

import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class AuthorizationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);
    private DatabaseHandler databaseHandler;

    public AuthorizationFilter() {
    }

    @Autowired
    public AuthorizationFilter( DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        String uuid = null;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies){
           if (cookie.getName().equals("uuid")){
               uuid=cookie.getValue();
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
                log.debug("Incorrect uuid.");
            }
        } else {
            log.error("Uuid is null.");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}