package org.s2f.mb.service.db;

import org.s2f.mb.model.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    public void addProduct(Product p) {
        String insert = "INSERT INTO food (name, weight, price, priceByOneGramm, kcal, isCooked) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = DBConnection.getInstance().prepareStatement(insert);

            log.info("Create a statement to DB - adding.");
            prSt.setString(1, p.getName());
            prSt.setInt(2, p.getWeight());
            prSt.setDouble(3, p.getPrice());
            prSt.setDouble(4, p.priceByOneGramm());
            prSt.setInt(5, p.getKcal());
            prSt.setBoolean(6, p.getIsCooked());

            prSt.executeUpdate();
            log.info("Product is added to DB.");

            prSt.close();
            log.info("Statement is closed.");
        } catch (SQLException e) {
            log.error("Not connected to DB.");
            e.printStackTrace();
        }
    }
}
