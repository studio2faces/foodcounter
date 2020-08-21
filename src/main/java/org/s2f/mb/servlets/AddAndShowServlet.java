package org.s2f.mb.servlets;

import org.s2f.mb.model.dto.Product;
import org.s2f.mb.service.Injector;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class AddAndShowServlet {
    private static final Logger log = LoggerFactory.getLogger(AddAndShowServlet.class);
    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    public AddAndShowServlet() {
        mapper = Injector.getObjectMapper();
        databaseHandler = Injector.getDatabaseHandler();
    }

    protected void doPost(HttpServletRequest request, PrintWriter output) throws IOException {
        Product p = mapper.requestToProduct(request);
        // установила isCooked=false прямо в сервлете add, потому что сервлет готовки будет ставить true
        p.setCooked(false);
        log.debug("{} is created.", p);

        try {
            databaseHandler.addProduct(p);
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<p>" + p.getName() + " is added by " + LocalUser.getLoggedUser().getLogin() + ".</p>");
        } catch (Exception e) {
            output.println("HTTP/1.1 503 Service Unavailable");
        }
    }

    protected void doGet(HttpServletRequest request, PrintWriter output) throws IOException {
        try {
            String jsonData = mapper.getJsonDataFromList(databaseHandler.getAllProductsByUuid(LocalUser.getLoggedUser().getUuid()));

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: application/json");
            output.println();
            output.write(jsonData);
        } catch (Exception e) {
            output.println("HTTP/1.1 503 Service Unavailable");
        }
    }
}