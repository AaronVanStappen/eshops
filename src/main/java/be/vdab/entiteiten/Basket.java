package be.vdab.entiteiten;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private int id;
    private int productId;
    private float amount;
    private List<Product> basket;

    public Basket(int id, int productId, float amount) {
        this.setId(id);
        this.setProductId(productId);
        this.setAmount(amount);
        basket = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getBasket() {
        return basket;
    }

    public int getProductId() {
        return productId;
    }

    private void setProductId(int productId) {
        this.productId = productId;
    }

    public float getAmount() {
        return amount;
    }

    private void setAmount(float amount) {
        this.amount = amount;
    }
}
