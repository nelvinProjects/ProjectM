package Server.Backbone;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	@GET
	@Produces("Application/json")
	@Path("/print")
	public String sayHello() {
		return gson.toJson("I made it HEREEEEEE! :)");
	}
}
