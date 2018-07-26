package Server.Activities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class ActivityBuilder {
    private int activityID;
    private int clientID;
    private String description = "Yet to be added :(";
    private boolean active;
    private LocalDate date;
    private LocalTime time;
    private boolean advert;
    private TreeSet<String> tags;

    public ActivityBuilder setID(int id){
        this.activityID = id;
        return this;
    }

    public ActivityBuilder setClient(int clientID){
        this.clientID = clientID;
        return this;
    }

    public ActivityBuilder activityDesc(String description){
        this.description = description;
        return this;
    }

    public ActivityBuilder activityLive(boolean active){
        this.active = active;
        return this;
    }

    public ActivityBuilder date(LocalDate date){
        this.date = date;
        return this;
    }

    public ActivityBuilder time(LocalTime time){
        this.time = time;
        return this;
    }

    public ActivityBuilder advert(boolean advert){
        this.advert = advert;
        return this;
    }

    public ActivityBuilder preferences(TreeSet tags){
        this.tags = tags;
        return this;
    }

    public Activity build(){
        return new Activity(activityID,clientID, description, active, date, time, advert, tags);
    }

}
