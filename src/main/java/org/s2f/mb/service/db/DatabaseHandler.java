package org.s2f.mb.service.db;

import org.s2f.mb.model.dto.Product;

import java.sql.*;

public class DatabaseHandler {

    public void addProduct(Product p) {
        String insert = "INSERT INTO food (name, weight, price, priceByOneGramm, kcal, isCooked) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = DBConnection.getInstance().prepareStatement(insert);
            System.out.println("Connection!!!");
            prSt.setString(1, p.getName());
            prSt.setInt(2, p.getWeight());
            prSt.setDouble(3, p.getPrice());
            prSt.setDouble(4, p.priceByOneGramm());
            prSt.setInt(5, p.getKcal());
            prSt.setBoolean(6, p.getIsCooked());

            prSt.executeUpdate();

            prSt.close();
            System.out.println("Connection is closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
