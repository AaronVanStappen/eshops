package be.vdab.entiteiten;

import java.sql.Date;

public class OrderView {
    private int prodId;
    private String prodName;
    private Date date;
    private double price;
    private int amount;
    private double totalPrice;

    public OrderView(int prodId, String prodName, Date date, double price, int amount, double totalPrice ) {
        setProdId(prodId);
        setProdName(prodName);
        setDate(date);
        setPrice(price);
        setAmount(amount);
        setTotalPrice(totalPrice);
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return prodId + ", " + prodName + ", " + date + ", " + price + ", " + amount + ", " + totalPrice;
    }
}
