package be.vdab.entiteiten;

import be.vdab.dao.*;
import be.vdab.dao.impl.*;
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
        System.out.println(test3.findCustomers("AaronVS"));
        System.out.println(test3.findByLoginAndUsername("JulieM", "vier321"));
        OrderDao test4 = new OrderDaoImpl();
        test4.findOrdersForCustomers(test3.findCustomers("AaronVS"))
                .forEach(System.out::println);
        System.out.println(test3.findCustomers("AaronVS").getId());
        test4.saveOrder(new Order(0, "mastercard", 5, Date.valueOf("2017-8-25"), 2, 1));
        BasketDao test5 = new BasketDaoImpl();
        test5.addProductToBasket(new Product(0, "Magic Mouse", 64.99, 5), 2);
        test5.addProductToBasket(new Product(5, "Magic Trackpad", 74.99, 7), 1);
        test5.addProductToBasket(new Product(9, "Magic Screen", 449.99, 3), 1);
        System.out.println(test5.toString());
        test5.removeProductFromBasket(new Product(0, "Magic Mouse", 64.99, 5));
        System.out.println(test5.getBasket());
        test5.clearBasket();
        System.out.println(test5.getBasket());
    }
}