package be.vdab.entiteiten;

import be.vdab.dao.CustomerDao;
import be.vdab.dao.OrderDao;
import be.vdab.dao.ProductDao;
import be.vdab.dao.ShopDao;
import be.vdab.dao.impl.CustomerDaoImpl;
import be.vdab.dao.impl.OrderDaoImpl;
import be.vdab.dao.impl.ProductDaoImpl;
import be.vdab.dao.impl.ShopDaoImpl;

public class TestApp {
    public static void main(String[] args) {
        ShopDao test = new ShopDaoImpl();
        test.listAllShops().forEach(System.out::println);
        ProductDao test2 = new ProductDaoImpl();
        test2.findProducts("Mac").forEach(System.out::println);
        CustomerDao test3 = new CustomerDaoImpl();
        System.out.println(test3.findCustomers("Van Stappen", "Aaron", "AaronVS"));
        System.out.println(test3.findByLoginAndUsername("JulieM", "vier321"));
        OrderDao test4 = new OrderDaoImpl();
        CustomerDao customer = new CustomerDaoImpl();
        test4.findOrdersForCustomers(customer.findCustomers("Menten", "Julie", "JulieM")).forEach(System.out::println);
    }
}
