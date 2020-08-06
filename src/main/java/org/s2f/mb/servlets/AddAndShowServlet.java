package org.s2f.mb.servlets;

import org.json.simple.JSONArray;
import org.s2f.mb.model.dto.Product;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;

@Controller
@Component
public class AddAndShowServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddAndShowServlet.class);

    private ObjectMapper mapper;
    private DatabaseHandler databaseHandler;

    @Autowired
    public AddAndShowServlet(ObjectMapper mapper, DatabaseHandler databaseHandler) {
        this.mapper = mapper;
        this.databaseHandler = databaseHandler;
    }

    @Override
    @PostMapping("/AddAndShowServlet")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product p = mapper.requestToProduct(request);
        // установила isCooked=false прямо в сервлете add, потому что сервлет готовки будет ставить true
        p.setCooked(false);
        log.debug("{} is created.", p);

        databaseHandler.addProduct(p);

        response.getWriter().println(p.getName() + " is added by " + LocalUser.getLoggedUser().getLogin() + ".");
    }

    @Override
    @GetMapping("/AddAndShowServlet")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        JSONArray jsonArray = mapper.getJsonArrayFromList(databaseHandler.getAllProductsByUuid(LocalUser.getLoggedUser().getUuid()));

        response.getWriter().write(jsonArray.toJSONString());
        response.getWriter().flush();
    }
}