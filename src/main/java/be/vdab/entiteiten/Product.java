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

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private int getStock() {
        return stock;
    }

    private void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return getId()==product.getId() && Double.compare(product.getPrice(), getPrice())==0
               && getStock()==product.getStock() && getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getName().hashCode();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getStock();
        return result;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + stock;
    }
}
