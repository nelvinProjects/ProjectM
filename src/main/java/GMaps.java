import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Unit;

import Server.Activities.Activity;
import Server.Activities.ActivityDatabase;
import Server.Connections.Database;

public class GMaps {

	private String key = "AIzaSyDat1VsRU9kMI2h5k_g0jBVq5ctezyMvRA";
	private GeoApiContext context = new GeoApiContext.Builder().apiKey(key).build();

	public double getDistance(String to, String from) {
		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			DistanceMatrix result = req.origins(from).destinations(to).units(Unit.IMPERIAL)
					.await();
			
			return Double.parseDouble(result.rows[0].elements[0].distance.humanReadable.split(" ")[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void distanceFromPostcode(String postcode, List<Activity> values){
//		List<Activity> distanceFound = new ArrayList();
		values.forEach(v -> v.setDistance(getDistance(v.getPostcode(), postcode)));
//		return distanceFound;
//		return values;
	}

	public static void main(String[] args) {
////		String fromPlace = "M50 3YJ";
//		GMaps gMaps = new GMaps();
////		System.out.println(gMaps.getDistance("M50 3YJ", "M5 4WT"));
////		gMaps.getDistance("M3 4EN", fromPlace);
////		
////		String hash = BCrypt.hashpw("password", BCrypt.gensalt(12));
////		String hash2 = BCrypt.hashpw("password", BCrypt.gensalt(12));
////		String hash3 = BCrypt.hashpw("password", BCrypt.gensalt(12));
////		System.out.println(hash);
////		System.out.println(hash2.length());
////		System.out.println(hash3.length());
////		
////		System.out.println(BCrypt.checkpw("password", hash));
////		System.out.println(BCrypt.checkpw("password", hash2));
////		System.out.println(BCrypt.checkpw("password", hash3));
		Database database = new Database();
		database.setupDB();
		ActivityDatabase aDatabase = new ActivityDatabase();
//		List<Activity> values = aDatabase.retrieveActivity();
//////		values.forEach((k, v) -> System.out.println(k + ":" + v.getActivityID() + v.getTitle()));
////		TreeMap<Integer, Activity> distanced = gMaps.distanceFromPostcode("M50 3YJ", values);
//		gMaps.distanceFromPostcode("M50 3YJ", values);
//		values.forEach(v -> System.out.println(v.getActivityID() + ":" + v.getTitle() + " "+ v.getPostcode() + " " + v.getDistance()));
	
		
		List<Activity> everyActivity = new ArrayList<Activity>();
		Gson gson = new Gson();
		everyActivity.add(new Activity(1, 3, "mountain", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "MN1 2NI", 18, 9.99, 6.6));
		everyActivity.add(new Activity(2, 5, "Korfball", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M40 7RL", 18, 9.99, 3.2));
		everyActivity.add(new Activity(3, 6, "Jujutsu", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M14 7LJ", 18, 9.99, 4.3));
		everyActivity.add(new Activity(4, 3, "Handball", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M40 2TN", 18, 9.99, 3.5));
		everyActivity.add(new Activity(5, 4, "Cricket", "Mountain biking is the sport of riding bicycles off-road, often over rough terrain, using specially designed mountain bikes. Mountain bikes share similarities with other bikes but incorporate features designed to enhance durability and performance in rough terrain. (Wikipedia)", 
				true, LocalDate.of(2018, 8, 1), LocalTime.of(12, 15), true, 15, 
				"15", "Street", "Manchester", "M20 2TH", 18, 9.99, 1.0));
		
//		System.out.println(gson.toJson(everyActivity));
		List<Activity> sortedEveryActivity = everyActivity.stream().sorted(Comparator.comparing(Activity::getDistance)).collect(Collectors.toList());
		for(Activity each : sortedEveryActivity) {
			System.out.println(each.getDistance() + "  "+ each.getTitle());
		}
		
		System.out.println(aDatabase.retrieveSpecificActivity(2).getTitle());
	
	}
	
}