package org.s2f.mb.servlets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.s2f.mb.model.dto.Product;
import org.s2f.mb.service.db.DBConnection;
import org.s2f.mb.service.db.DatabaseHandler;
import org.s2f.mb.service.mappers.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddAndShowServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddAndShowServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductMapper pm = new ProductMapper();
        JSONObject jsonObject = pm.requestParamsToJSON(request);
        log.info("Get JSON object: {}", jsonObject.toJSONString());
        Product p = pm.mapperJsonToDto(jsonObject.toJSONString());
        // установила isCooked=false прямо в сервлете add, потому что сервлет готовки будет ставить true
        p.setCooked(false);
        log.info("{} is created.", p);

        new DatabaseHandler().addProduct(p);

        response.getWriter().println(p.getName() + " is added.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Statement stmt = DBConnection.getInstance().createStatement();
            log.info("Create a statement to DB - Show all products.");
            ResultSet res = stmt.executeQuery("SELECT * FROM food");
            ProductMapper pm = new ProductMapper();
            Product dto = null;
            JSONArray jsonArray = new JSONArray();
            while (res.next()) {
                dto = new Product(
                        res.getString("name"),
                        res.getInt("weight"),
                        res.getDouble("price"),
                        res.getInt("kcal"),
                        res.getBoolean("isCooked")
                );
                jsonArray.add(pm.mapperDtoToJson(dto));
            }
            response.getWriter().println("JSON array:");
            response.getWriter().println(jsonArray);
            stmt.close();
            log.info("Statement is closed.");
        } catch (SQLException e) {
            log.info("Not connected to DB.");
            e.printStackTrace();
        }
    }
}