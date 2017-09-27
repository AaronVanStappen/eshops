package be.vdab.dao.impl;

import be.vdab.dao.OrderDao;
import be.vdab.entiteiten.Customer;
import be.vdab.entiteiten.Order;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.jdbc.ConnectionDao.getConnection;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    @Override
    public List<Order> findOrdersForCustomers(Customer customer) {
        String sql1 = "select * from orders where customerId=?;";
        List<Order> orderList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql1)) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            stmt.setInt(1, customer.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderList.add(new Order(rs.getInt("id"), rs.getString("payment_method"),
                            rs.getInt("order_total"),
                            rs.getDate("date"), rs.getInt("customerId"), rs.getInt("eshopId")));
                }
            }
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("customer not found - " + e);
        }
        return orderList;
    }

    @Override
    public void saveOrder(Order order) {
        String sql2 = "insert into orders (id, payment_method, order_total, date, customerId, eshopId) " +
                "values(null, ?, ?, ?, ?, ?);";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql2)) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            stmt.setString(1, order.getPaymethod());
            stmt.setDouble(2, order.getOrderTotal());
            stmt.setDate(3, order.getDate());
            stmt.setInt(4, order.getCustomerId());
            stmt.setInt(5, order.getEshopId());
            int i = stmt.executeUpdate();
            System.out.println(i);
            con.commit();
        } catch (SQLException e) {
            LOGGER.error("could not save order - " + e);
        }
    }
}
