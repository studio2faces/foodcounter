package org.s2f.mb.service.db;

import org.s2f.mb.model.dto.Product;
import org.s2f.mb.model.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.jstl.sql.Result;
import java.sql.*;
import java.util.UUID;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    public void addProduct(Product p) {
        String insert = "INSERT INTO food (name, weight, price, priceByOneGramm, kcal, isCooked, users_uuid) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = DBConnection.getInstance().prepareStatement(insert);

            DBConnection.getInstance().setAutoCommit(false);
            DBConnection.getInstance().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            log.info("Create a statement to DB - adding.");
            prSt.setString(1, p.getName());
            prSt.setInt(2, p.getWeight());
            prSt.setDouble(3, p.getPrice());
            prSt.setDouble(4, p.priceByOneGramm());
            prSt.setInt(5, p.getKcal());
            prSt.setBoolean(6, p.getIsCooked());
            prSt.setString(7, p.getUsers_uuid().toString());

            prSt.executeUpdate();
            DBConnection.getInstance().commit();
            log.info("Product is added to DB.");

            prSt.close();
            log.info("Statement is closed.");
        } catch (SQLException e) {
            try {
                DBConnection.getInstance().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            log.error("Exception {}", e);
        }
    }

    public void addUser(User user) {
        String insert = "INSERT INTO users (users_uuid, login) VALUES (?,?)";

        try {
            PreparedStatement prSt = DBConnection.getInstance().prepareStatement(insert);
            log.info("Create a statement to DB - User adding.");

            DBConnection.getInstance().setAutoCommit(false);
            DBConnection.getInstance().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            prSt.setString(1, user.getUuid().toString());
            prSt.setString(2, user.getLogin());

            prSt.executeUpdate();
            DBConnection.getInstance().commit();
            log.info("User {} is added to DB.", user.getLogin());
            prSt.close();
            log.info("Statement is closed.");
        } catch (SQLException e) {
            try {
                DBConnection.getInstance().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            log.error("Exception {}", e);
        }
    }

    public User getUserByUUID(UUID uuid) {
        User user = null;
        try {
            Statement st = DBConnection.getInstance().createStatement();
            DBConnection.getInstance().setAutoCommit(false);

            ResultSet res = st.executeQuery("SELECT login FROM users WHERE users_uuid='" + uuid.toString() + "'");
            while (res.next()) {
                user = new User(res.getString("login"), uuid);
            }

            DBConnection.getInstance().commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public UUID getUUIDByLogin(String login) {
        UUID uuid = null;
        try {
            Statement st = DBConnection.getInstance().createStatement();
            DBConnection.getInstance().setAutoCommit(false);

            ResultSet res = st.executeQuery("SELECT users_uuid FROM users WHERE login ='" + login + "'");
            while (res.next()) {
                String uuidString = res.getString("users_uuid");
                uuid = UUID.fromString(uuidString);
                log.debug("DBH: uuid = {}", uuid);
            }
            DBConnection.getInstance().commit();
            st.close();
        } catch (SQLException e) {
            log.error("SQL ERROR in DBH:getUUIDByLogin(String login)", e);
        }

        return uuid;
    }
}
