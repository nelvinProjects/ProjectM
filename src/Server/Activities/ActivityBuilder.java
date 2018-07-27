package Server.Activities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class ActivityBuilder {
    private int activityID, clientID, quantity;
    private String description = "Yet to be added :(";
    private boolean active, advert;
    private LocalDate date;
    private LocalTime time;
    private TreeSet<String> tags;
    private String streetAddress1, streetAddress2, city, county, postcode;

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

    public ActivityBuilder preferences(TreeSet tags){
        this.tags = tags;
        return this;
    }

    public ActivityBuilder advert(boolean advert){
        this.advert = advert;
        return this;
    }

    public ActivityBuilder streetAddress1(String streetAddress1){
        this.streetAddress1 = streetAddress1;
        return this;
    }

    public ActivityBuilder streetAddress2(String streetAddress2){
        this.streetAddress2 = streetAddress2;
        return this;
    }

    public ActivityBuilder city(String city){
        this.city = city;
        return this;
    }

    public ActivityBuilder county(String county){
        this.county = county;
        return this;
    }

    public ActivityBuilder postcode(String postcode){
        this.postcode = postcode;
        return this;
    }

    public ActivityBuilder quantity(int quantity){
        this.quantity = quantity;
        return this;
    }

    public Activity build(){
        return new Activity(activityID,clientID, description, active, date, time, advert, tags, quantity,
                streetAddress1, streetAddress2, city, county, postcode);
    }

}