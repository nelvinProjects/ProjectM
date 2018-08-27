import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/index")
public class Index {
		
	@GET
	@Path("/indexPostcode")
	public Response getPostcode() {
		try {
			return Response.temporaryRedirect(new URI("../activity.html")).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
		
}
