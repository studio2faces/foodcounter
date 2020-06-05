package org.s2f.mb.servlets;

import org.s2f.mb.model.DTO.Product;
import org.s2f.mb.model.logic.Const;
import org.s2f.mb.model.logic.DatabaseHandler;
import org.s2f.mb.model.mappers.ProductMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DatabaseHandler dbh = new DatabaseHandler();
        try {
            Statement stmt = dbh.getDbConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM " + Const.FOOD_TABLE);
            ProductMapper pm = new ProductMapper();
            Product dto = null;
            while (res.next()) {
                // response.getWriter().println(res.getString(Const.PRODUCT_NAME));
                dto = new Product(
                        res.getString(2),
                        res.getInt(3),
                        res.getDouble(4),
                        res.getInt(5),
                        res.getString(6)
                );

                response.getWriter().println(pm.mapperDtoToJson(dto));
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
