package org.s2f.mb.servlets;

import org.json.simple.JSONArray;
import org.s2f.mb.model.dto.Product;
import org.s2f.mb.service.Injector;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class AddAndShowServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddAndShowServlet.class);
    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    public AddAndShowServlet() {
        mapper = Injector.getObjectMapper();
        databaseHandler = Injector.getDatabaseHandler();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Product p = mapper.requestToProduct(request);



        // установила isCooked=false прямо в сервлете add, потому что сервлет готовки будет ставить true
        p.setCooked(false);
        log.debug("{} is created.", p);

        databaseHandler.addProduct(p);

        response.getWriter().println(p.getName() + " is added by " + LocalUser.getLoggedUser().getLogin() + ".");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        JSONArray jsonArray = databaseHandler.showAllProductsByUuid(LocalUser.getLoggedUser().getUuid());

        response.getWriter().write(jsonArray.toJSONString());
        response.getWriter().flush();
    }
}