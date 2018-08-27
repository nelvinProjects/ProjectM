
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Server.Activities.Activity;
import Server.Booking.OrderDatabase;
import Server.Connections.Database;
import Server.Connections.Login;

@Path("/process")
public class Process {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/login")
	public String checkLogin(@FormParam("email") String email, 
			@FormParam("password") String password) {
		Gson gson = new Gson();
		Database database = new Database();
		database.setupDB();
		Login login = new Login();
		String[] value = login.checkAccountDetails(email, password);
		return gson.toJson(value);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/clientName")
	public String retrieveClientName(@QueryParam("clientid") int id) {
		Gson gson = new Gson();
		Login login = new Login();
		return gson.toJson(login.getClientDetails(id));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/userorders")
	public String getUserOrders(@QueryParam("clientid") int id) {
		Gson gson = new Gson();
		Database database = new Database();
		database.setupDB();
		OrderDatabase orderDatabase = new OrderDatabase();
		return gson.toJson(orderDatabase.retrieveOrders(id));
	}
}
