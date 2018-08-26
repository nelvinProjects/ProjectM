package Server.Activities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Server.Connections.Database;

public class ActivityDatabase {

	public List<Activity> retrieveActivity() {
		List<Activity> act = new ArrayList<Activity>();
		Activity activity;
		Statement statement;
		String sql = "SELECT * FROM activities INNER JOIN address ON activities.postcode "
				+ "= address.postcode WHERE ACTIVE = 1;";
		try {
			statement = Database.dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				activity = new ActivityBuilder().setID(rs.getInt(1)).setClient(rs.getInt(2)).title(rs.getString(3))
						.activityDesc(rs.getString(4)).setPrice(rs.getDouble(5)).setAge(rs.getInt(6))
						.advert(rs.getBoolean(7)).date(rs.getDate(8).toLocalDate()).time(rs.getTime(9).toLocalTime())
						.activityLive(rs.getBoolean(10)).streetAddress1(rs.getString(11)).quantity(rs.getInt(12))
						.postcode(rs.getString(13)).streetAddress2(rs.getString(15)).city(rs.getString(16)).build();
				act.add(activity);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return act;
	}
	
	public Activity retrieveSpecificActivity(int id) {
		Activity activity;
		PreparedStatement statement;
		String sql = "SELECT * FROM activities INNER JOIN address ON activities.postcode "
				+ "= address.postcode WHERE ACTIVE = 1 AND activityID = ?;";
		try {
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				activity = new ActivityBuilder().setID(rs.getInt(1)).setClient(rs.getInt(2)).title(rs.getString(3))
						.activityDesc(rs.getString(4)).setPrice(rs.getDouble(5)).setAge(rs.getInt(6))
						.advert(rs.getBoolean(7)).date(rs.getDate(8).toLocalDate()).time(rs.getTime(9).toLocalTime())
						.activityLive(rs.getBoolean(10)).streetAddress1(rs.getString(11)).quantity(rs.getInt(12))
						.postcode(rs.getString(13)).streetAddress2(rs.getString(15)).city(rs.getString(16)).build();
				return activity;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addActivity(Integer clientID, String title, int quantity, int age, String description, double price,
			boolean AD, boolean active, String address1, String address2, String city, String postcode, Date date,
			LocalTime time) {
		if (checkActivityExist(clientID, title)) {
			PreparedStatement statement = null;
			String sql = "INSERT INTO activities (activityID, clientID, title, description,price,"
					+ "age, AD, date, time, active, address1, quantity, postcode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				if (checkAddressExist(postcode)) {
					String sqlTwo = "INSERT INTO address(postcode, streetName, city) VALUES (?,?,?);";
					statement = Database.dbConnection.prepareStatement(sqlTwo);
					statement.setString(1, postcode);
					statement.setString(2, address2);
					statement.setString(3, city);
					statement.execute();
				}
				statement = null;
				int id = Database.getMaxID("activities", "activityID");
				statement = Database.dbConnection.prepareStatement(sql);
				statement.setInt(1, id);
				statement.setInt(2, clientID);
				statement.setString(3, title);
				statement.setString(4, description);
				statement.setDouble(5, price);
				statement.setInt(6, age);
				statement.setBoolean(7, AD);
				statement.setDate(8, date);
				statement.setTime(9, Time.valueOf(time));
				statement.setBoolean(10, active);
				statement.setString(11, address1);
				statement.setInt(12, quantity);
				statement.setString(13, postcode);
				statement.execute();
				ActivityImages ai = new ActivityImages();

				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean checkActivityExist(int clientID, String title) {
		PreparedStatement statement;
		String sql = "SELECT * FROM activities WHERE clientID = ? AND title = ?;";
		try {
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setInt(1, clientID);
			statement.setString(2, title);
			ResultSet rs = statement.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}
			rs.close();
			if (count > 0)
				return false;
			else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkAddressExist(String postcode) {
		PreparedStatement statement;
		String sql = "SELECT * FROM address WHERE postcode = ?;";
		try {
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setString(1, postcode.toUpperCase());
			ResultSet rs = statement.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}
			rs.close();
			if (count > 0)
				return false;
			else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
