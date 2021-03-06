package Server.Activities;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityBuilder {
	private int activityID, clientID, quantity, age;
	private String description = "Yet to be added :(";
	private double price;
	private boolean active, advert;
	private LocalDate date;
	private LocalTime time;
//    private TreeSet<String> tags;
	private String streetAddress1, streetAddress2, city, postcode, title;

	public ActivityBuilder setID(int id) {
		this.activityID = id;
		return this;
	}

	public ActivityBuilder setAge(int age) {
		this.age = age;
		return this;
	}

	public ActivityBuilder setPrice(double price) {
		this.price = price;
		return this;
	}

	public ActivityBuilder setClient(int clientID) {
		this.clientID = clientID;
		return this;
	}

	public ActivityBuilder activityDesc(String description) {
		this.description = description;
		return this;
	}

	public ActivityBuilder title(String title) {
		this.title = title;
		return this;
	}

	public ActivityBuilder activityLive(boolean active) {
		this.active = active;
		return this;
	}

	public ActivityBuilder date(LocalDate date) {
		this.date = date;
		return this;
	}

	public ActivityBuilder time(LocalTime time) {
		this.time = time;
		return this;
	}

	public ActivityBuilder advert(boolean advert) {
		this.advert = advert;
		return this;
	}

	public ActivityBuilder streetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
		return this;
	}

	public ActivityBuilder streetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
		return this;
	}

	public ActivityBuilder city(String city) {
		this.city = city;
		return this;
	}

	public ActivityBuilder postcode(String postcode) {
		this.postcode = postcode;
		return this;
	}

	public ActivityBuilder quantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public Activity build() {
		return new Activity(activityID, clientID, title, description, active, date, time, advert, quantity,
				streetAddress1, streetAddress2, city, postcode, age, price, 0);
	}
}