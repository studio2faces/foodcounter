package org.s2f.mb.model.logic;

import org.s2f.mb.model.dto.Product;

import java.sql.*;

public class DatabaseHandler  {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/fridge_counter_db?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "root");

        return dbConnection;
    }

    public void addProduct(Product p) {
        String insert = "INSERT INTO "
                + Const.FOOD_TABLE + "("
                + Const.FT_NAME + ","
                + Const.FT_WEIGHT + ","
                + Const.FT_PRICE + ","
                + Const.FT_PRICE1G + ","
                + Const.FT_KCAL100 + ","
                + Const.FT_ISCOOKED + ")"
                + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            System.out.println("Connection!!!");
            prSt.setString(1, p.getName());
            prSt.setInt(2, p.getWeight());
            prSt.setDouble(3, p.getPrice());
            prSt.setDouble(4, p.getPriceFor1g());
            prSt.setInt(5, p.getKcal100g());
            prSt.setBoolean(6, p.getIsCooked());

            prSt.executeUpdate();

            getDbConnection().close();
            System.out.println("Connection is closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
