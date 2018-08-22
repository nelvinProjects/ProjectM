package Server.Activities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Activity extends Location {
    private int activityID, clientID, quantity, age;
    private String description, title;
    private boolean active, advert;
    private LocalDate date;
    private LocalTime time;
//    private TreeSet<String> tags;

    public Activity(int activityID, int clientID, String title, String description, boolean active, LocalDate date,
                    LocalTime time, boolean advert, int quantity, String address1, String address2, String city,
                    String postcode, int age) {
        super(address1, address2, city, postcode);
        this.title = title;
        this.activityID = activityID;
        this.clientID = clientID;
        this.description = description;
        this.active = active;
        this.date = date;
        this.time = time;
        this.advert = advert;
        this.quantity = quantity;
        this.age = age;

    }

    public int getActivityID() {
        return activityID;
    }
    
    public int getAge() {
    	return age;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getDescription() {
        return description;
    }
    
    public String getTitle() {
    	return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isAdvert() {
        return advert;
    }

    public void setAdvert(boolean advert) {
        this.advert = advert;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
