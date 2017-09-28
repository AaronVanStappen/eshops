package be.vdab.entiteiten;

import java.sql.Date;

public class Order {
    private int id;
    private String paymethod;
    private double orderTotal;
    private Date date;
    private int customerId;
    private int eshopId;

    public Order(String paymethod, double orderTotal, Date date, int customerId, int eshopId) {
        this.setPaymethod(paymethod);
        this.setOrderTotal(orderTotal);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setEshopId(eshopId);
    }

    public Order(int id, String paymethod, double orderTotal, Date date, int customerId, int eshopId) {
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

    private void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    private void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    private void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEshopId() {
        return eshopId;
    }

    private void setEshopId(int eshopId) {
        this.eshopId = eshopId;
    }

    @Override
    public String toString() {
        return id + ", " + paymethod + ", " + orderTotal + ", " + date + ", " + customerId + ", " + eshopId;
    }
}
