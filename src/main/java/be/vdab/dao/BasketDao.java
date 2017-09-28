package be.vdab.dao;

import be.vdab.entiteiten.Basket;
import be.vdab.entiteiten.Product;

public interface BasketDao {
    void checkOutBasket(Basket basket);

    void saveOrUpdateBasket(Basket basket);

    void addProductToBasket(Product product, double amount);

    void removeProductFromBasket(Product product);

    Basket getBasket();

    void clearBasket();
}
