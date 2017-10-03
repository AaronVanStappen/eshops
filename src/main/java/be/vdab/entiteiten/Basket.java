package be.vdab.entiteiten;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private List<Product> producten = new ArrayList<>();

    public Basket() {}

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
        return producten.toString();
    }
}
