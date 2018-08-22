import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;

public class GMaps {
//	after create account, js a value and get it in server and redirect to 
//	login
	private String key = "AIzaSyDat1VsRU9kMI2h5k_g0jBVq5ctezyMvRA";
	private GeoApiContext context = new GeoApiContext.Builder().apiKey(key).build();

	public void getDistance(String to, String from) {
		try {
			Gson jsonConvert = new Gson();
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			DistanceMatrix result = req.origins(from).destinations(to).units(Unit.IMPERIAL)
//		               .mode(TravelMode..DRIVING)
//		               .avoid(RouteRestriction.TOLLS)
//		               .language("en-US")
					.await();

			System.out.println("1 " + result.toString());
			System.out.println("2 " + jsonConvert.toJson(result));
			
			System.out.println("3 " + jsonConvert.toJson(result.rows[0].elements[0].distance.humanReadable));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		String fromPlace = "M50 3YJ";
//		GMaps gMaps = new GMaps();
//		gMaps.getDistance("M3 4EN", fromPlace);
		
		String hash = BCrypt.hashpw("password", BCrypt.gensalt(12));
		String hash2 = BCrypt.hashpw("password", BCrypt.gensalt(12));
		String hash3 = BCrypt.hashpw("password", BCrypt.gensalt(12));
		System.out.println(hash);
		System.out.println(hash2.length());
		System.out.println(hash3.length());
		
		System.out.println(BCrypt.checkpw("password", hash));
		System.out.println(BCrypt.checkpw("password", hash2));
		System.out.println(BCrypt.checkpw("password", hash3));
	}
}