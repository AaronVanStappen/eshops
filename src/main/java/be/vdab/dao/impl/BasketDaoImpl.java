package be.vdab.dao.impl;

import be.vdab.jdbc.ConnectionDao;
import be.vdab.dao.BasketDao;
import be.vdab.entiteiten.Basket;
import be.vdab.entiteiten.Order;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Connection;


public class BasketDaoImpl implements BasketDao {
    private Basket basket = new Basket();
    private static final Logger LOGGER = Logger.getLogger(BasketDaoImpl.class);

    @Override
    public String toString() {
        return basket.toString();
    }

    @Override
    public void addToDB(Order order, int productId, int amount) throws SQLException {
        String insertOrder = "insert into orders values (null, ?, ?, ?, ?, ?);";
        String insertOrderDetail = "insert into orderdetail values (null, ?, ?, ?);";
        String updateStock = "update products set stock = stock - ? where id = ?;";
        try (Connection con = ConnectionDao.getConnection()) {
            try (PreparedStatement stmt1 = (PreparedStatement) con.prepareStatement(insertOrder)){
                con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                con.setAutoCommit(false);
                stmt1.setString(1, order.getPaymethod());
                stmt1.setDouble(2, order.getOrderTotal());
                stmt1.setDate(3, order.getDate());
                stmt1.setInt(4, order.getCustomerId());
                stmt1.setInt(5, order.getEshopId());
                stmt1.execute();
                con.commit();
            } catch (SQLException e) {
                LOGGER.error("basket could not be added to database - " + e);
                con.rollback();
            }
            try (PreparedStatement stmt2 = (PreparedStatement) con.prepareStatement(insertOrderDetail)) {
                con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                con.setAutoCommit(false);
                stmt2.setInt(1, amount);
                stmt2.setInt(2, getLastId());
                stmt2.setInt(3, productId);
                stmt2.execute();
                con.commit();
            } catch (SQLException e) {
                LOGGER.error("basket could not be added to database - " + e);
                con.rollback();
            }
            try (PreparedStatement stmt3 = (PreparedStatement) con.prepareStatement(updateStock)) {
                con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                con.setAutoCommit(false);
                stmt3.setInt(1, amount);
                stmt3.setInt(2, productId);
                stmt3.execute();
                con.commit();
            } catch (SQLException e) {
                LOGGER.error("basket could not be added to database - " + e);
                con.rollback();
            }
        }
    }

    @Override
    public int getLastId() {
        String getId = "select max(id) as lastId from orders;";
        int id = 0;
        try (Connection con = ConnectionDao.getConnection(); Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(getId)) {
            if (rs.next()) {
                id = rs.getInt("lastId");
            }
        } catch (SQLException e) {
            LOGGER.error("last id in orders - " + e);
        }
        return id;
    }
}
