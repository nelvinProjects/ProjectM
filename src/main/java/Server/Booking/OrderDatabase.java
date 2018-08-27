package Server.Booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Server.Activities.Activity;
import Server.Activities.ActivityBuilder;
import Server.Connections.Database;

public class OrderDatabase {

	public List<Activity> retrieveOrders(int customerID) {
		List<Activity> order = new ArrayList<Activity>();
		Activity activity;
		PreparedStatement statement;
		String sql = "SELECT * FROM activitiesdb.`order` inner join activitiesdb.activities on activitiesdb.order.activityID "
				+ "= activitiesdb.activities.activityID inner join \r\n"
				+ "activitiesdb.address on activitiesdb.activities.postcode = "
				+ "activitiesdb.address.postcode where customerID = ?;";
		try {
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				activity = new ActivityBuilder().setID(rs.getInt(5)).setClient(rs.getInt(6)).title(rs.getString(7))
						.activityDesc(rs.getString(8)).setPrice(rs.getDouble(9)).setAge(rs.getInt(10))
						.advert(rs.getBoolean(11)).date(rs.getDate(12).toLocalDate()).time(rs.getTime(13).toLocalTime())
						.activityLive(rs.getBoolean(14)).streetAddress1(rs.getString(15)).quantity(rs.getInt(16))
						.postcode(rs.getString(18)).streetAddress2(rs.getString(19)).city(rs.getString(20)).build();
				order.add(activity);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	public int getQuantity(int activityID) {
		PreparedStatement statement;
		String sql = "SELECT quantity FROM activitiesdb.activities WHERE activityID = ?;";
		try {
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setInt(1, activityID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception f) {
			f.printStackTrace();
		}
		return 0;
	}

	public void updateQuantity(int activityID) {
		int quantity = getQuantity(activityID);
		System.out.println("Quantity " + quantity);
		PreparedStatement statement;
		String sql = "UPDATE activitiesdb.activities SET quantity= ? WHERE activityID= ?;";
		try {
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setInt(1, --quantity);
			statement.setInt(2, activityID);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addOrder(int activityID, int customerID, LocalDateTime time) {
		PreparedStatement statement = null;
		String sql = "INSERT INTO activitiesdb.order (orderID, activityID, customerID, timeBought) "
				+ "VALUES (?,?,?,?);";
		try {
			int id = Database.getMaxID("order", "orderID");
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setInt(2, activityID);
			statement.setInt(3, customerID);
			statement.setTimestamp(4, Timestamp.valueOf(time));
			System.out.println(statement);
			statement.execute();
			updateQuantity(activityID);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}