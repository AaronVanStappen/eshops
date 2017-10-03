package be.vdab.dao.impl;

import be.vdab.dao.BasketDao;
import be.vdab.entiteiten.Basket;
import be.vdab.entiteiten.Order;
import be.vdab.entiteiten.Product;
import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import java.util.ArrayList;

import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.*;

public class BasketDaoImplTest {
    private BasketDao basket = new BasketDaoImpl();

    private ArrayList<Product> data = Lists.newArrayList(
            new Product(1, "guitar1", 3599.99, 5),
            new Product(2, "guitar2", 13599.99, 5),
            new Product(3, "guitar3", 599.99, 5),
            new Product(4, "guitar1", 3599.99, 5),
            new Product(5, "guitar6", 3599.99, 5),
            new Product(10, "guitar10", 3999.79,25)
    );

    @Before
    public void init() {
        for(Product product : data) {
            basket.addProductToBasket(product);
        }
    }

    @Test
    public void saveOrUpdateBasket() {
        BasketDao basket2 = new BasketDaoImpl();
        basket2.saveOrUpdateBasket(basket.getBasket());
        assertThat(basket2).isInstanceOf(BasketDaoImpl.class);
        assertThat(basket2.getBasket().getProducten()).hasSize(6);
        assertThat(basket2.getBasket().getProducten())
                .contains(new Product(10, "guitar10", 3999.79, 25));
        Product product = new Product(11, "guitar11", 14999.99, 12);
        basket2.addProductToBasket(product);
        assertThat(basket2.getBasket().getProducten()).contains(product);
        basket.removeProductFromBasket(product);
        assertThat(basket2.getBasket().getProducten()).doesNotContain(product);
    }

    @Test
    public void addProductToBasket() {
        Product product = new Product(11, "guitar11", 1249.00, 5);
        basket.addProductToBasket(product);
        assertThat(basket.getBasket().getProducten()).hasSize(7);
        assertThat(basket.getBasket().getProducten())
                .contains(new Product(11, "guitar11", 1249.00, 5));
    }

    @Test
    public void removeProductFromBasket() {
        basket.removeProductFromBasket(new Product(1, "guitar1", 3599.99, 5));
        assertThat(basket.getBasket().getProducten()).hasSize(5);
        assertThat(basket.getBasket().getProducten())
                .doesNotContain(new Product(1, "guitar1", 3599.99, 5));
    }

    @Test
    public void getBasket() {
        assertThat(basket.getBasket()).isInstanceOf(Basket.class);
        assertThat(basket.getBasket().getProducten()).hasSize(6);
        assertThat(basket.getBasket().getProducten())
                .contains(new Product(1, "guitar1", 3599.99, 5));
    }

    @Test
    public void clearBasket() {
        basket.clearBasket();
        assertThat(basket.getBasket().getProducten()).isEmpty();
    }
}