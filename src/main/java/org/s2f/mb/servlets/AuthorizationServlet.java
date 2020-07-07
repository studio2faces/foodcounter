package org.s2f.mb.servlets;

import org.json.simple.JSONObject;
import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.Injector;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);
    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    public AuthorizationServlet() {
        mapper = Injector.getObjectMapper();
        databaseHandler = Injector.getDatabaseHandler();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");


        User user = mapper.requestToUser(request);
        user.setUuid(databaseHandler.getUuidByLogin(user.getLogin()));
        log.debug("Authorization: {}", user.toString());

        if (user.getUuid() == null) {
            log.info("New user.");
            user.generateUuid();
            databaseHandler.addUser(user);
            log.debug("New user - {}", user.toString());
        } else {
            log.debug("User exists - {}", user.toString());
        }

        response.getWriter().write(mapper.userUuidToJson(user));
    }
}
