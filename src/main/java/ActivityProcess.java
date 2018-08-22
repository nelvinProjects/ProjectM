import java.sql.Date;
import java.sql.Time;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import Server.Activities.ActivityDatabase;

@Path("/activity")
public class ActivityProcess {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/addactivity")
	public void addActivity(@FormParam("title") String title, @FormParam("description")
	String description,@FormParam("price") double price, @FormParam("age") int age,
	@FormParam("advertise") String ad, @FormParam("actdate") Date date,
	@FormParam("acttime") String time, @FormParam("quantity") int quantity,
	@FormParam("address1") String address, @FormParam("street") String street,
	@FormParam("city") String city, @FormParam("postcode") String postcode,
	@FormParam("id") String id) {
		System.out.println(title);
		System.out.println(description);
		System.out.println(price);
		System.out.println(age);
		System.out.println(ad);
		System.out.println(date);
		System.out.println(time);
		System.out.println(quantity);
		System.out.println(address);
		System.out.println(street);
		System.out.println(city);
		System.out.println(postcode);
		System.out.println(id);

		ActivityDatabase activityDatabase = new ActivityDatabase();
		activityDatabase.addActivity(Integer.valueOf(id), title, quantity, age, description, price, Boolean.valueOf(ad), 
				true, address, street, city, postcode, date, Time.valueOf(time));
	}
}
