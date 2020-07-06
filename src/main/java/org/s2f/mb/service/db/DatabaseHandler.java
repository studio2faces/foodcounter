package org.s2f.mb.service.db;

import org.json.simple.JSONArray;
import org.s2f.mb.model.dto.Product;
import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);
    private Connection dbConnection = DBConnection.getInstance();

    public void addProduct(Product p) {
        String insert = "INSERT INTO food (name, weight, price, priceByOneGramm, kcal, isCooked, users_uuid) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = DBConnection.getInstance().prepareStatement(insert);

            dbConnection.setAutoCommit(false);
            dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            log.info("Create a statement to DB.");
            prSt.setString(1, p.getName());
            prSt.setInt(2, p.getWeight());
            prSt.setDouble(3, p.getPrice());
            prSt.setDouble(4, p.priceByOneGramm());
            prSt.setInt(5, p.getKcal());
            prSt.setBoolean(6, p.getIsCooked());
            prSt.setString(7, LocalUser.getLoggedUser().getUuid());

            prSt.executeUpdate();

            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            log.info("Product {} is added to DB.", p.getName());
            prSt.close();
            log.info("Statement is closed.");
        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            log.error("SQL ERROR", e);
        }
    }

    public void addUser(User user) {
        String insert = "INSERT INTO users (users_uuid, login) VALUES (?,?)";

        try {
            PreparedStatement prSt = DBConnection.getInstance().prepareStatement(insert);
            log.info("Create a statement to DB.");

            dbConnection.setAutoCommit(false);
            dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            prSt.setString(1, user.getUuid());
            prSt.setString(2, user.getLogin());

            prSt.executeUpdate();
            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            log.info("User {} is added to DB.", user.getLogin());
            prSt.close();
            log.info("Statement is closed.");
        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            log.error("SQL ERROR", e);
        }
    }

    public User getUserByUuid(String uuid) {
        User user = null;
        try {
            Statement st = DBConnection.getInstance().createStatement();
            dbConnection.setAutoCommit(false);

            ResultSet res = st.executeQuery("SELECT login FROM users WHERE users_uuid='" + uuid + "' LIMIT 1");
            while (res.next()) {
                user = new User(res.getString("login"), uuid);
            }

            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            st.close();
        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                log.error("SQL error", e1);
            }
            log.error("No user in DB with uuid {}.", uuid, e);
        }
        return user;
    }

    public String getUUIDByLogin(String login) {
        String uuid = null;
        try {
            Statement st = DBConnection.getInstance().createStatement();
            dbConnection.setAutoCommit(false);

            ResultSet res = st.executeQuery("SELECT users_uuid FROM users WHERE login ='" + login + "'");
            while (res.next()) {
                uuid = res.getString("users_uuid");
                log.debug("uuid = {}", uuid);
            }
            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            st.close();
        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                log.error("SQL ERROR", e1);
            }
            log.error("SQL ERROR", e);
        }

        return uuid;
    }

    public JSONArray showAllProductsByUuid(String uuid) {
        String select = "SELECT * FROM food WHERE users_uuid=?";
        JSONArray jsonArray = new JSONArray();
        Product product = null;

        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement(select);
            log.info("Create a statement to DB.");

            dbConnection.setAutoCommit(false);
            dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            ps.setString(1, uuid);

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                product = new Product(
                        res.getString("name"),
                        res.getInt("weight"),
                        res.getDouble("price"),
                        res.getInt("kcal"),
                        res.getBoolean("isCooked"),
                        res.getString("users_uuid")
                );
                jsonArray.add(new ObjectMapper().productToJson(product));
                log.debug("Show product {}", product.getName());
            }

            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            ps.close();
            log.info("Statement is closed.");

        } catch (SQLException e) {
            try {
                dbConnection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            log.error("SQL ERROR", e);
        }
        return jsonArray;
    }
}