import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.mindrot.jbcrypt.BCrypt;

import Server.Connections.Database;
import Server.Connections.Login;

@Path("/register")
public class Register {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addUser(@FormParam("fname") String fname, @FormParam("sname") String sname,
			@FormParam("email") String email, @FormParam("dob") Date dob, @FormParam("postcode") String postcode,
			@FormParam("password") String password) {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		Database database = new Database();
		database.setupDB();
		Login login = new Login();
		login.createAccount(email, hash, false, fname, sname, dob, postcode);
		database.closeConnection();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/client")
	public void addClient(@FormParam("cname") String cname, @FormParam("email") String email,
			@FormParam("password") String password) {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		Database database = new Database();
		database.setupDB();
		Login login = new Login();
		login.createAccount(email, hash, true, cname, "", null, "");
		database.closeConnection();
	}

}
