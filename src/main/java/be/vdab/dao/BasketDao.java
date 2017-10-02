package be.vdab.dao;

import be.vdab.entiteiten.Basket;
import be.vdab.entiteiten.Order;
import be.vdab.entiteiten.Product;

import java.sql.SQLException;

public interface BasketDao {

    void saveOrUpdateBasket(Basket basket);

    void addProductToBasket(Product product);

    void removeProductFromBasket(Product product);

    Basket getBasket();

    void clearBasket();

    void addToDB(Order order, int productId, int amount) throws SQLException;

    int getLastId();
}
