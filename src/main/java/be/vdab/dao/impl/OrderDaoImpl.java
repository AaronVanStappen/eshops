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
    private List<Order> orderList;

    @Override
    public List<Order> findOrdersForCustomers(Customer customer) {
        String sql = "select * from orders where customerId=?";
        orderList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            stmt.setInt(1, customer.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderList.add(new Order(rs.getInt("id"), rs.getString("payment_method"), rs.getInt("order_total"),
                            rs.getDate("date"), rs.getInt("customerId"), rs.getInt("e-shopId")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("customer not found - " + e);
        }
        return orderList;
    }

    @Override
    public void saveOrder(Order order) {

    }
}
