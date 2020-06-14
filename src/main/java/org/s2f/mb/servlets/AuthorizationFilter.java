package org.s2f.mb.servlets;

import org.json.simple.JSONObject;
import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.setContentType("application/json");

        ProductMapper pm = new ProductMapper();
        JSONObject jsonObject = pm.requestParamsToJSON(request);

        User user = pm.mapperJsonToUser(jsonObject.toJSONString());
        user.setUuid(new DatabaseHandler().getUUIDByLogin(user.getLogin()));
        log.debug("{}", user.toString());

        if (user.getUuid() == null) {
            log.info("New user.");
            user.setUuid();
            new DatabaseHandler().addUser(user);
            LocalUser.getLocalUser().set(user);
            log.debug("New User. LocalUser = {}", LocalUser.getLocalUser().get().toString());
        } else {
            LocalUser.getLocalUser().set(new DatabaseHandler().getUserByUUID(user.getUuid()));
            log.debug("User exists. LocalUser = {}", LocalUser.getLocalUser().get().toString());
        }

        response.getWriter().write(pm.mapperUsersUUIDToJson(user));
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}