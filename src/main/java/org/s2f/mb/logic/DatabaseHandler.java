package org.s2f.mb.logic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);


        return dbConnection;
    }

    public void addProduct(String name, int weight, double price, int kcal) {
        String insert = "INSERT INTO " + Const.FOOD_TABLE + "(" +
                Const.PRODUCT_NAME + "," + Const.PRODUCT_WEIGHT_G + "," +
                Const.PRODUCT_PRICE_R + "," + Const.PRODUCT_KCAL100 + "," + Const.PRODUCT_ISCOOKED + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            System.out.println("Connection!!!");
            prSt.setString(1, name);
            prSt.setInt(2, weight);
            prSt.setDouble(3, price);
            prSt.setInt(4, kcal);
            prSt.setString(5, "n");

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
