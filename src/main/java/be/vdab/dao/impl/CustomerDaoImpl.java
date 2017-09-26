package be.vdab.dao.impl;

import be.vdab.dao.CustomerDao;
import be.vdab.entiteiten.Customer;
import be.vdab.entiteiten.Product;
import be.vdab.entiteiten.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.jdbc.ConnectionDao.getConnection;

public class CustomerDaoImpl implements CustomerDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class.getName());
    private Customer customer;
    private User user;

    @Override
    public Customer findCustomers(String name, String firstname, String username) {
        String sql = "select * from customers where name=? and first_name=? and username=?;";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            stmt.setString(1, name);
            stmt.setString(2, firstname);
            stmt.setString(3, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                            rs.getString("name"), rs.getString("first_name"),
                            rs.getString("e-mail"), rs.getString("delivery_address"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("customer not found - " + e);
        }
        return customer;
    }

    @Override
    public User findByLoginAndUsername(String username, String password) {
        String sql = "select * from customers where username=? and password=?;";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(rs.getInt("id"),rs.getString("username"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("customer not found - " + e);
        }
        return user;
    }
}
