package be.vdab.entiteiten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private Map<Product, Double> producten = new HashMap<>();

    public Basket() {}

    public void addToBasket(Product product, double amount) {
        producten.put(product, amount);
    }

    public void removeFromBasket(Product product) {
        producten.remove(product);
    }

    public Map<Product, Double> getProducten() {
        return producten;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket)) return false;

        Basket basket = (Basket) o;

        return producten.equals(basket.producten);
    }

    @Override
    public int hashCode() {
        return producten.hashCode();
    }

    @Override
    public String toString() {
        return producten.keySet().toString() + ", " + producten.values().toString();
    }
}
