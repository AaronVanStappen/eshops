package be.vdab.dao.impl;

import be.vdab.dao.CustomerDao;
import be.vdab.dao.OrderDao;
import be.vdab.entiteiten.Order;
import org.junit.Test;
import java.sql.Date;

import static org.assertj.core.api.Assertions.*;

public class OrderDaoImplTest {
    private OrderDao order = new OrderDaoImpl();
    private Order order2 = new Order(0, "PayPall", 25, Date.valueOf("2017-07-12"), 1, 1);
    private CustomerDao customer = new CustomerDaoImpl();

    @Test
    public void findOrdersForCustomers() {
        assertThat(order.findOrdersForCustomers(customer.findCustomers("Menten", "Julie",
                "JulieM"))).isNotNull();
        assertThat(order.findOrdersForCustomers(customer.findCustomers("Menten", "Julie",
                "JulieM")).get(0).getId()).isEqualTo(2);
        assertThat(order.findOrdersForCustomers(customer.findCustomers("Menten", "Julie",
                "JulieM"))).hasSize(16);
    }

    @Test
    public void saveOrder() {
        order.saveOrder(order2);
        assertThat(order.findOrdersForCustomers(customer.findCustomers("Van Stappen", "Aaron",
                "AaronVS")).get(2).getPaymethod()).isEqualTo("PayPall");
    }
}