package org.s2f.mb.servlets;

import org.s2f.mb.model.dto.Product;
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
                dto = new Product(
                        res.getString(Const.FT_NAME),
                        res.getInt(Const.FT_WEIGHT),
                        res.getDouble(Const.FT_PRICE),
                        res.getInt(Const.FT_KCAL100),
                        res.getBoolean(Const.FT_ISCOOKED)
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
