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
    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);
    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    public AuthorizationFilter(ObjectMapper mapper, DatabaseHandler databaseHandler) {
        this.mapper = mapper;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        JSONObject jsonObject = mapper.requestParamsToJSON(request);
        String uuid = (String) jsonObject.get("uuid");

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

    public void doFilterWithoutChain(ServletRequest request) throws IOException, ServletException {
        JSONObject jsonObject = mapper.requestParamsToJSON(request);
        String uuid = (String) jsonObject.get("uuid");

        if (uuid != null) {
            try {
                User loggedUser = databaseHandler.getUserByUuid(uuid);
                log.debug("Authorized {}", loggedUser.toString());
                LocalUser.setLoggedUser(loggedUser);
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