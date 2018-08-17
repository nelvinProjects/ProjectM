package Server.Booking;

import java.time.LocalDateTime;

public class Order {
    private int orderID, customerID, activityID;
    private double price;
    private LocalDateTime orderedAt;

    public Order(int orderID, int customerID, int activityID, double price, LocalDateTime dateTime) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.activityID = activityID;
        this.price = price;
        this.orderedAt = dateTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }
}
