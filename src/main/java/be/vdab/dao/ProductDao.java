package be.vdab.dao;

import be.vdab.entiteiten.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findProducts(String productName);
    List<Product> sortProducts();
    List<Product> sortProductsByPriceAsc();
    List<Product> sortProductsByPriceDesc();
    List<Product> getProducts();
    Product get(int i);
}
