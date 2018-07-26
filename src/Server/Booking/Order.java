package Server.Booking;

import java.time.LocalDateTime;

public class Order {
    private int orderID, clientID, activityID;
    private double price;
    private LocalDateTime orderedAt;

    public Order(int orderID, int clientID, int activityID, double price, LocalDateTime dateTime){
        this.orderID = orderID;
        this.clientID = clientID;
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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
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
