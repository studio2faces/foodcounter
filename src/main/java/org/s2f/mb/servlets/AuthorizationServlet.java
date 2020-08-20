package org.s2f.mb.servlets;

import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.Injector;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

public class AuthorizationServlet {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationServlet.class);
    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    public AuthorizationServlet() {
        mapper = Injector.getObjectMapper();
        databaseHandler = Injector.getDatabaseHandler();
    }

    protected void doPost(HttpServletRequest request, PrintWriter output) {
        User user = mapper.requestToUser(request);
        user.setUuid(databaseHandler.getUuidByLogin(user.getLogin()));
        log.debug("Authorization: {}", user.toString());

        try {
            if (user.getUuid() == null) {
                log.info("New user.");
                user.generateUuid();
                databaseHandler.addUser(user);
                log.debug("New user - {}", user.toString());
            } else {
                log.debug("User exists - {}", user.toString());
            }
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: application/json");
            output.println();
            output.write(mapper.userUuidToJson(user));
        } catch (Exception e) {
            output.println("HTTP/1.1 503 Service Unavailable");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<p>Something wrong with database.</p>");
        }

    }
}
