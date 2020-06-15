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
    private ObjectMapper om;
    private DatabaseHandler dbh;

    public AuthorizationServlet() {
        om = Injector.getObjectMapper();
        dbh = Injector.getDatabaseHandler();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        JSONObject jsonObject = om.requestParamsToJSON(request);

        User user = om.jsonToUser(jsonObject.toJSONString());
        user.setUuid(dbh.getUUIDByLogin(user.getLogin()));
        log.debug("Authorization: {}", user.toString());

        if (user.getUuid() == null) {
            log.info("New user.");
            user.generateUuid();
            dbh.addUser(user);
            log.debug("New user - {}", user.toString());
        } else {
            log.debug("User exists - {}", user.toString());
        }

        response.getWriter().write(om.userUuidToJson(user));
    }
}
