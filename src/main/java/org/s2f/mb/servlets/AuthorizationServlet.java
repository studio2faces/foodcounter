package org.s2f.mb.servlets;

import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Component
public class AuthorizationServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationServlet.class);
    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    @Autowired
    public AuthorizationServlet(ObjectMapper mapper, DatabaseHandler databaseHandler) {
        this.mapper = mapper;
        this.databaseHandler = databaseHandler;
    }

    @Override
    @PostMapping("/AuthorizationServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
