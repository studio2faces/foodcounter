package org.s2f.mb.servlets;

import org.json.simple.JSONObject;
import org.s2f.mb.config.SpringConfig;
import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@Order(2)
@Component
public class AuthorizationFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    // @Autowired
    // private ApplicationContext context;
    //  AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

    /*private ObjectMapper mapper=configApplicationContext.getBean("objectMapper", ObjectMapper.class);
    private DatabaseHandler databaseHandler=configApplicationContext.getBean("databaseHandler", DatabaseHandler.class);*/

    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    public AuthorizationFilter() {
        /*mapper = new ObjectMapper();
        databaseHandler = new DatabaseHandler();*/
    }

    @Autowired
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}