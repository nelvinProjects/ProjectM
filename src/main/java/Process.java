
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Server.Connections.Database;
import Server.Connections.Login;

@Path("/process")
public class Process {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public String checkLogin(@FormParam("email") String email, 
			@FormParam("password") String password) {
		Gson gson = new Gson();
//		System.out.println(email);
//		System.out.println(password);
		Database database = new Database();
		database.setupDB();
		Login login = new Login();
		String[] value = login.checkAccountDetails(email, password);
		return gson.toJson(value);
//		loggedIn(value);
//		System.out.println(value.length);
//		if (value.length > 0) {
//			return gson.toJson(value);
//		}else {
//			return "";
//		}
	}
	
//	@GET
//	@Produces
//	@Path("/loggedin")
//	public String loggedIn(String[] value) {
//		Gson gson = new Gson();
////		String[] value = login.checkAccountDetails(email, password);
//		System.out.println(value.length);
//		if (value.length > 0) {
//			return gson.toJson(value);
//		}else {
//			return "";
//		}
//	}
}
