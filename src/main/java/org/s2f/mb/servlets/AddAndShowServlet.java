package org.s2f.mb.servlets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.s2f.mb.model.dto.Product;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper pm = new ObjectMapper();
        JSONObject jsonObject = pm.requestParamsToJSON(request);
        log.info("Get JSON object: {}", jsonObject.toJSONString());

        Product p = pm.jsonToProduct(jsonObject.toJSONString());
        // установила isCooked=false прямо в сервлете add, потому что сервлет готовки будет ставить true
        p.setCooked(false);
        log.debug("{} is created.", p);

        new DatabaseHandler().addProduct(p);

        response.getWriter().println(p.getName() + " is added by " + LocalUser.getLoggedUser().getLogin()+".");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        JSONArray jsonArray = new DatabaseHandler().showAllProductsByUuid(LocalUser.getLoggedUser().getUuid().toString());

        response.getWriter().write(jsonArray.toJSONString());
        response.getWriter().flush();
    }
}