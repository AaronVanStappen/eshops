package be.vdab.entiteiten;

import java.sql.Date;

public class Order {
    private int id;
    private String paymethod;
    private int orderTotal;
    private Date date;
    private int customerId;
    private int eshopId;

    public Order(int id, String paymethod, int orderTotal, Date date, int customerId, int eshopId) {
        this.setId(id);
        this.setPaymethod(paymethod);
        this.setOrderTotal(orderTotal);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setEshopId(eshopId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEshopId() {
        return eshopId;
    }

    public void setEshopId(int eshopId) {
        this.eshopId = eshopId;
    }
}
