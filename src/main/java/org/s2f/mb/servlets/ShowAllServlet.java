package org.s2f.mb.servlets;

import org.s2f.mb.model.dto.Product;
import org.s2f.mb.model.logic.DBConnection;
import org.s2f.mb.model.mappers.ProductMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   //   DatabaseHandler dbh = new DatabaseHandler();
        try {
           // Statement stmt = dbh.getDbConnection().createStatement();
            Connection connection = DBConnection.getInstance();
            Statement stmt = connection.createStatement();
            System.out.println("Connection - showAll");
            ResultSet res = stmt.executeQuery("SELECT * FROM food");
            ProductMapper pm = new ProductMapper();
            Product dto = null;
            while (res.next()) {
                dto = new Product(
                        res.getString("name"),
                        res.getInt("weight"),
                        res.getDouble("price"),
                        res.getInt("kcal"),
                        res.getBoolean("isCooked")
                );
                response.getWriter().println(pm.mapperDtoToJson(dto));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
