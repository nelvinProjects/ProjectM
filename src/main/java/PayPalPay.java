import java.time.LocalDateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import Server.Booking.OrderDatabase;

@Path("/buyActivity")
public class PayPalPay {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void purchaseActivityAction(@FormParam("userid") int userID, @FormParam("activityID") int activityID) {
		System.out.println(userID + activityID);
		OrderDatabase orderDatabase = new OrderDatabase();
		orderDatabase.addOrder(activityID, userID, LocalDateTime.now());
	}
}
