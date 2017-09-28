package be.vdab.dao.impl;

import be.vdab.dao.ProductDao;
import be.vdab.entiteiten.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ProductDaoImplTest {
    private ProductDao products = new ProductDaoImpl();

    @Test
    public void findProducts() {
        assertThat(products).isNotNull();
        assertThat(products.findProducts("Iphone")).hasSize(4);
        assertThat(products.findProducts("Iphone"))
                .contains(new Product(14, "Iphone X 256gb", 1299.99, 8));
    }

    @Test
    public void sortProducts() {
        products.sortProducts();
        Product p = products.get(0);
        assertThat(p).isEqualTo(new Product(1, "MacBook Pro 15", 2355.50, 5));
    }

    @Test
    public void sortProductsByPriceAsc() {
        products.sortProductsByPriceAsc();
        Product p = products.get(8);
        assertThat(p).isEqualTo(new Product(1, "MacBook Pro 15", 2355.50, 5));
    }

    @Test
    public void sortProductsByPriceDesc() {
        products.sortProductsByPriceDesc();
        Product p = products.get(0);
        assertThat(p).isEqualTo(new Product(1, "MacBook Pro 15", 2355.50, 5));
        assertThat(products.getProducts()).contains(new Product(1, "MacBook Pro 15", 2355.50, 5));
    }
}