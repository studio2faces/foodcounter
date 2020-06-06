package org.s2f.mb.servlets;

import org.json.simple.JSONObject;
import org.s2f.mb.model.DTO.Product;
import org.s2f.mb.model.logic.DatabaseHandler;
import org.s2f.mb.model.mappers.ProductMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AddToFridgeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductMapper pm = new ProductMapper();
        JSONObject jsonObject = pm.requestParamsToJSON(request);
        String jsonStr = jsonObject.toJSONString();
        System.out.println(jsonStr);

        Product p = pm.mapperJsonToDto(jsonObject.toJSONString());
        p.setCooked(false);
        System.out.println(p);

        new DatabaseHandler().addProduct(p);

        response.getWriter().println(p.getName() + " is added.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
