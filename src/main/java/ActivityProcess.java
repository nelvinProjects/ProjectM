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
import Server.Connections.Database;

@Path("/activity")
public class ActivityProcess {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/addactivity")
	public void addActivity(@FormParam("title") String title, @FormParam("description")
	String description,@FormParam("price") double price, @FormParam("age") int age,
	@FormParam("advertise") boolean ad, @FormParam("actdate") Date date,
	@FormParam("acttime") String time, @FormParam("quantity") int quantity,
	@FormParam("address1") String address, @FormParam("street") String street,
	@FormParam("city") String city, @FormParam("postcode") String postcode,
	@FormParam("id") String id) {
		ActivityDatabase activityDatabase = new ActivityDatabase();
		activityDatabase.addActivity(Integer.valueOf(id), title.trim(), quantity, age, description.trim(), price, ad, 
				true, address.trim(), street.trim(), city.trim(), postcode.trim(), date, LocalTime.parse(time));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/specificActivities")
	public String retrieveActivitiesFromID(@QueryParam("idchosen") int id, @QueryParam("postcode")
	String postcode) {
		Gson gson = new Gson();
		GMaps gMaps = new GMaps();
		ActivityDatabase activityDatabase = new ActivityDatabase();
		Activity specific = activityDatabase.retrieveSpecificActivity(id);
		specific.setDistance(gMaps.getDistance(specific.getPostcode(), postcode));
		return gson.toJson(specific);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allActivities")
	public String retrieveActivitiesFromPostcode(@QueryParam("postcodegiven") String postcode) {
		System.out.println(postcode);
		Database database = new Database();
		database.setupDB();
		ActivityDatabase activityDatabase = new ActivityDatabase();
		List<Activity> everyActivity = activityDatabase.retrieveActivity();
		GMaps gMaps = new GMaps();
		gMaps.distanceFromPostcode(postcode, everyActivity);
		Gson gson = new Gson();
		List<Activity> sortedEveryActivity = everyActivity.stream().sorted(Comparator.comparing(Activity::getDistance)).collect(Collectors.toList());
		return gson.toJson(sortedEveryActivity);
	}
}
