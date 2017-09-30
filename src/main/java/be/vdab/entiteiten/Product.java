package be.vdab.entiteiten;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int amount;

    public Product(String name, double price, int stock) {
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
    }

    public Product(int id, String name, double price, int stock) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
    }

    public Product(int id, String name, double price, int stock, int amount) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setAmount(amount);
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return this.getId() == product.getId();

        /*return getId()==product.getId() && Double.compare(product.getPrice(), getPrice())==0
               && getStock()==product.getStock() && getName().equals(product.getName());*/
    }

    @Override
    public int hashCode() {
        /*int result;
        long temp;
        result = getId();
        result = 31 * result + getName().hashCode();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getStock();
        return result;*/
        return id;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + stock + ", " + amount;
    }
}
