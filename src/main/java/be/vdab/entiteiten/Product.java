package be.vdab.entiteiten;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    private void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + stock;
    }
}
