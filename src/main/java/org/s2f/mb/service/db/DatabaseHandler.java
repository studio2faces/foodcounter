package org.s2f.mb.service.db;

import org.json.simple.JSONArray;
import org.s2f.mb.model.dto.Product;
import org.s2f.mb.model.dto.User;
import org.s2f.mb.service.LocalUser;
import org.s2f.mb.service.mappers.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);
    private Connection dbConnection = DBConnection.getInstance();

    public void addProduct(Product p) {
        String insert = "INSERT INTO food (name, weight, price, priceByOneGramm, kcal, isCooked, uuid) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = dbConnection.prepareStatement(insert);

            dbConnection.setAutoCommit(false);
            dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            log.info("Create a statement to DB.");
            statement.setString(1, p.getName());
            statement.setInt(2, p.getWeight());
            statement.setDouble(3, p.getPrice());
            statement.setDouble(4, p.priceByOneGramm());
            statement.setInt(5, p.getKcal());
            statement.setBoolean(6, p.getIsCooked());
            statement.setString(7, LocalUser.getLoggedUser().getUuid());

            statement.executeUpdate();

            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            log.info("Product {} is added to DB.", p.getName());
            statement.close();
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
        String insert = "INSERT INTO users (uuid, login) VALUES (?,?)";

        try {
            PreparedStatement statement = dbConnection.prepareStatement(insert);
            log.info("Create a statement to DB.");

            dbConnection.setAutoCommit(false);
            dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement.setString(1, user.getUuid());
            statement.setString(2, user.getLogin());

            statement.executeUpdate();
            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            log.info("User {} is added to DB.", user.getLogin());
            statement.close();
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
        String select = "SELECT login FROM users WHERE uuid = ? LIMIT 1";
        User user = null;

        try {
            PreparedStatement statement = dbConnection.prepareStatement(select);
            log.info("Create a statement to DB.");

            dbConnection.setAutoCommit(false);
            dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement.setString(1, uuid);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString("login"), uuid);
                log.info("User with uuid {} exists.", uuid);
            } else {
                throw new SQLException("No user in DB with uuid " + uuid);
            }
            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            statement.close();
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

    public String getUuidByLogin(String login) {
        String uuid = null;
        String select = "SELECT uuid FROM users WHERE login = ? LIMIT 1";

        try {
            PreparedStatement statement = dbConnection.prepareStatement(select);
            log.info("Create a statement to DB.");

            dbConnection.setAutoCommit(false);
            dbConnection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                uuid = resultSet.getString("uuid");
                log.debug("uuid = {}", uuid);
            } else throw new SQLException("No user in DB with login " + login);

            dbConnection.commit();
            dbConnection.setAutoCommit(true);
            statement.close();
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

    public List<Product> getAllProductsByUuid(String uuid) {
        String select = "SELECT * FROM food WHERE uuid=?";
        List<Product> products = new ArrayList<>();
      //  JSONArray jsonArray = new JSONArray();
        Product product = null;

        try {
            PreparedStatement ps = dbConnection.prepareStatement(select);
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
                        res.getString("uuid")
                );
                products.add(product);
             //   jsonArray.add(new ObjectMapper().productToJson(product));
            }
            log.debug("Create products list {}", products);

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
        return products;
    }
}