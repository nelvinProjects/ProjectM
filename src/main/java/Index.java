import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/index")
public class Index {
	private Gson gson = new Gson();

//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	@Path("home")
//	public InputStream welcome() {
//		File f = new File(pathname);
//		   return new FileInputStream(f);
//	}
//	
	public static String postcode = "";

	
	@GET
	@Produces("Application/json")
	@Path("/print")
	public String sayHello() {
		return gson.toJson("I made it HEREEEEEE! :)");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/indexPostcode")
	public void getPostcode(@FormParam("indexPost") String post) {
		postcode = post.toUpperCase();
		System.out.println(postcode);
	}
	
	@GET
	@Produces("Application/json")
	@Path("/postcode")
	public String printPostcode() {
		System.out.println(postcode);
		return gson.toJson(postcode);
	}
	
}
