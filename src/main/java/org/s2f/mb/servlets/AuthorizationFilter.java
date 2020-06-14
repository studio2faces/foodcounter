package org.s2f.mb.servlets;

import org.json.simple.JSONObject;
import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.setContentType("application/json");

        ObjectMapper pm = new ObjectMapper();
        JSONObject jsonObject = pm.requestParamsToJSON(request);

        User user = pm.jsonToUser(jsonObject.toJSONString());
        user.generateUuid(new DatabaseHandler().getUUIDByLogin(user.getLogin()));
        log.debug("Authorization: {}", user.toString());

        if (user.getUuid() == null) {
            log.info("New user.");
            user.generateUuid();
            new DatabaseHandler().addUser(user);
            LocalUser.setLoggedUser(user);
            log.debug("New User. LocalUser = {}", LocalUser.getLoggedUser().toString());
        } else {
            LocalUser.setLoggedUser(new DatabaseHandler().getuserbyUuid(user.getUuid()));
            log.debug("User exists. LocalUser = {}", LocalUser.getLoggedUser().toString());
        }

        response.getWriter().write(pm.userUuidToJson(user));
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}