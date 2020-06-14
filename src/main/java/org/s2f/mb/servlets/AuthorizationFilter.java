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
import java.util.UUID;

public class AuthorizationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ObjectMapper om = new ObjectMapper();
        JSONObject jsonObject = om.requestParamsToJSON(request);
        String uuid = (String) jsonObject.get("users_uuid");

        if (uuid != null) {

            try {
                User loggedUser = new DatabaseHandler().getuserbyUuid(UUID.fromString(uuid));
                log.debug("Authorized {}", loggedUser.toString());
                LocalUser.setLoggedUser(loggedUser);
            } catch (NullPointerException e) {
                log.debug("Incorrect uuid.");
            }
        } else {
            log.error("User is null");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}