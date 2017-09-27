package be.vdab.dao.impl;

import be.vdab.dao.BasketDao;
import be.vdab.entiteiten.Basket;
import be.vdab.entiteiten.Product;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class BasketDaoImplTest {
    private BasketDao basket = new BasketDaoImpl();

    private ArrayList<Product> data = Lists.newArrayList(
            new Product(1, "guitar1", 3599.99, 5),
            new Product(2, "guitar2", 5999.99, 3),
            new Product(3, "guitar3", 2999.49, 25),
            new Product(4, "guitar4", 1999.99, 45),
            new Product(5, "guitar5", 9500.00, 5),
            new Product(6, "guitar6", 599.49, 150),
            new Product(7, "guitar7", 24999.99, 1),
            new Product(8, "guitar8", 799.49,35),
            new Product(9, "guitar9", 500_000.00, 1),
            new Product(10, "guitar10", 3999.79, 25)
    );

    @Before
    public void init() {
        for(Product product : data) {
            basket.addProductToBasket(product, 10);
        }
    }

    @Test
    public void saveOrUpdateBasket() {
        BasketDao basket2 = new BasketDaoImpl();
        basket2.saveOrUpdateBasket(basket.getBasket());
        assertThat(basket2).isInstanceOf(BasketDaoImpl.class);
        assertThat(basket2.getBasket().getProducten()).hasSize(10);
        assertThat(basket2.getBasket().getProducten())
                .containsKeys(new Product(10, "guitar10", 3999.79, 25));
        Product product = new Product(11, "guitar11", 14999.99, 12);
        basket2.addProductToBasket(product, 1);
        assertThat(basket2.getBasket().getProducten()).containsKeys(product);
        basket.removeProductFromBasket(product);
        assertThat(basket2.getBasket().getProducten()).doesNotContainKeys(product);
    }

    @Test
    public void addProductToBasket() {
        Product product = new Product(11, "guitar11", 1249.00, 5);
        basket.addProductToBasket(product, 1);
        assertThat(basket.getBasket().getProducten()).hasSize(11);
        assertThat(basket.getBasket().getProducten())
                .containsKeys(new Product(11, "guitar11", 1249.00, 5));
    }

    @Test
    public void removeProductFromBasket() {
        basket.removeProductFromBasket(new Product(1, "guitar1", 3599.99, 5));
        assertThat(basket.getBasket().getProducten()).hasSize(9);
        assertThat(basket.getBasket().getProducten())
                .doesNotContainKeys(new Product(1, "guitar1", 3599.99, 5));
    }

    @Test
    public void getBasket() {
        assertThat(basket.getBasket()).isInstanceOf(Basket.class);
        assertThat(basket.getBasket().getProducten()).hasSize(10);
        assertThat(basket.getBasket().getProducten())
                .containsKeys(new Product(1, "guitar1", 3599.99, 5));
    }

    @Test
    public void clearBasket() {
        basket.clearBasket();
        assertThat(basket.getBasket().getProducten()).isEmpty();
    }
}