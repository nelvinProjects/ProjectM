import java.util.List;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;

import Server.Activities.Activity;
import Server.Booking.OrderDatabase;
import Server.Connections.Database;

public class GMaps {

	private String key = "AIzaSyDat1VsRU9kMI2h5k_g0jBVq5ctezyMvRA";
	private GeoApiContext context = new GeoApiContext.Builder().apiKey(key).build();

	public double getDistance(String to, String from) {
		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			DistanceMatrix result = req.origins(from).destinations(to).units(Unit.IMPERIAL).await();

			return Double.parseDouble(result.rows[0].elements[0].distance.humanReadable.split(" ")[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void distanceFromPostcode(String postcode, List<Activity> values) {
		values.forEach(v -> v.setDistance(getDistance(v.getPostcode(), postcode)));
	}

	public static void main(String[] args) {
		Database database = new Database();
		database.setupDB();
		OrderDatabase orderDatabase = new OrderDatabase();
//		orderDatabase.retrieveOrders(1).forEach(v -> System.out.println(v.getTitle()));
		System.out.println(orderDatabase.retrieveOrders(1));
	}

}