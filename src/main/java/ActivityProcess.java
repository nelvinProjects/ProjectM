import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import Server.Activities.Activity;
import Server.Activities.ActivityDatabase;

@Path("/activity")
public class ActivityProcess {

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/addactivity")
	public void addActivity(@FormParam("title") String title, @FormParam("description")
	String description,@FormParam("price") double price, @FormParam("age") int age,
	@FormParam("advertise") boolean ad, @FormParam("actdate") Date date,
	@FormParam("acttime") String time, @FormParam("quantity") int quantity,
	@FormParam("address1") String address, @FormParam("street") String street,
	@FormParam("city") String city, @FormParam("postcode") String postcode,
	@FormParam("id") String id) {
		ActivityDatabase activityDatabase = new ActivityDatabase();
		activityDatabase.addActivity(Integer.valueOf(id), title, quantity, age, description, price, ad, 
				true, address, street, city, postcode, date, LocalTime.parse(time));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allActivities")
	public String retrieveActivitiesFromPostcode(@QueryParam("postcodegiven") String postcode) {
		System.out.println(postcode);
		List<Activity> everyActivity = new ArrayList<Activity>();
		Gson gson = new Gson();
		everyActivity.add(new Activity(1, 3, "Outdoor mountain", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "MN1 2NI", 18, 9.99, 3.0));
		everyActivity.add(new Activity(2, 5, "Korfball", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M40 7RL", 18, 9.99, 1.2));
		everyActivity.add(new Activity(3, 6, "Jujutsu", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M14 7LJ", 18, 9.99, 4.3));
		everyActivity.add(new Activity(4, 3, "Handball", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M40 2TN", 18, 9.99, 2.5));
		everyActivity.add(new Activity(5, 4, "Cricket", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M20 2TH", 18, 9.99, 6.6));
/*
 * 1.0:Korfball M5 4HU
3.2:Badminton M15 6WL
3.4:Jujutsu M30 0GX
3.7:Go-Kart Race M40 7RL
3.9:Volleyball M14 7LJ
4.3:Handball M27 4UF
6.4:Rock climbing M40 2TN
6.6:Outdoor Archery M23 0DQ
6.9:Cricket M20 2TH
 */
		List<Activity> sortedEveryActivity = everyActivity.stream().sorted(Comparator.comparing(Activity::getDistance)).collect(Collectors.toList());
		return gson.toJson(sortedEveryActivity);
	}
}
