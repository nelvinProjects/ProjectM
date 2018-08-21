import java.sql.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/register")
public class Register {

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addUser(@FormParam("fullname") String fullname, @FormParam("email") String email,
			@FormParam("dob") Date dob, @FormParam("postcode") String postcode, @FormParam("password") String password,
			@FormParam("preference") List<String> pref) {
		System.out.println(fullname);
		System.out.println(email);
		System.out.println(dob);
		System.out.println(postcode);
		System.out.println(password);
		System.out.println(pref.toString());

	}

}
