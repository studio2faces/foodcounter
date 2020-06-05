package org.s2f.mb.servlets;

import org.s2f.mb.model.logic.DatabaseHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AddToFridgeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        int productWeight = Integer.parseInt(request.getParameter("productWeight"));
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        int productKcal100g = Integer.parseInt(request.getParameter("productKcal"));

        double priceBy1Gr = (productPrice * 1.0) / productWeight;
        System.out.println("from do post " + productName);

        DatabaseHandler dbh = new DatabaseHandler();
        dbh.addProduct(productName, productWeight, priceBy1Gr, productKcal100g);

        response.getWriter().println(productName + " is added.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* String productName = request.getParameter("productName");
        int productWeight = Integer.parseInt(request.getParameter("productWeight"));
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        int productKcal100g = Integer.parseInt(request.getParameter("productKcal"));

        double priceBy1Gr = (productPrice * 1.0) / productWeight;
        System.out.println("from do get " + productName);

        DatabaseHandler dbh = new DatabaseHandler();
        dbh.addProduct(productName, productWeight, priceBy1Gr, productKcal100g);

        response.getWriter().println(productName + " is added.");*/

    }

}
