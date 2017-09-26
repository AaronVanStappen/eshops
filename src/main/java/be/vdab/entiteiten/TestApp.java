package be.vdab.entiteiten;

import be.vdab.dao.CustomerDao;
import be.vdab.dao.OrderDao;
import be.vdab.dao.ProductDao;
import be.vdab.dao.ShopDao;
import be.vdab.dao.impl.CustomerDaoImpl;
import be.vdab.dao.impl.OrderDaoImpl;
import be.vdab.dao.impl.ProductDaoImpl;
import be.vdab.dao.impl.ShopDaoImpl;
import org.apache.log4j.Logger;

import java.sql.*;

public class TestApp {
    private static final Logger LOGGER = Logger.getLogger(TestApp.class);

    public static void main(String[] args) {
        ShopDao test = new ShopDaoImpl();
        test.listAllShops().forEach(System.out::println);
        ProductDao test2 = new ProductDaoImpl();
        test2.findProducts("Mac").forEach(System.out::println);
        CustomerDao test3 = new CustomerDaoImpl();
        System.out.println(test3.findCustomers("Van Stappen", "Aaron", "AaronVS"));
        System.out.println(test3.findByLoginAndUsername("JulieM", "vier321"));
        OrderDao test4 = new OrderDaoImpl();
        test4.findOrdersForCustomers(test3.findCustomers("Van Stappen", "Aaron", "AaronVS"))
                .forEach(System.out::println);
        System.out.println(test3.findCustomers("Van Stappen", "Aaron", "AaronVS").getId());
        test4.saveOrder(new Order(0, "mastercard", 5, Date.valueOf("2017-8-25"), 2, 1));
    }
}