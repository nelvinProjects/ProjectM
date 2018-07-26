package Server.Activities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Activity {
    private int activityID;
    private int clientID;
    private String description;
    private boolean active;
    private LocalDate date;
    private LocalTime time;
    private boolean advert;
    private TreeSet<String> tags;

    public Activity(int activityID, int clientID, String description, boolean active, LocalDate date,
                    LocalTime time, boolean advert, TreeSet tags){
        this.activityID = activityID;
        this.clientID = clientID;
        this.description = description;
        this.active = active;
        this.date = date;
        this.time = time;
        this.advert = advert;
        this.tags = tags;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
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

    public void setDescription(String description) {
        this.description = description;
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

    public TreeSet<String> getTags() {
        return tags;
    }

    public void setTags(TreeSet<String> tags) {
        this.tags = tags;
    }
}
