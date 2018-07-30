package Server.Booking;

import Server.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDatabase {

    public ArrayList<String> retrieveOrders(int customerID) {
        ArrayList<String> order = new ArrayList<>();
        String eachOrder = "";
        Statement statement;
        String sql = "SELECT * FROM activitiesdb.order;";
        try {
            statement = Database.dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                eachOrder += rs.getInt(1);
                eachOrder += "," + rs.getInt(2);
                eachOrder += "," + rs.getInt(3);
                eachOrder += "," + rs.getTimestamp(4);
                order.add(eachOrder);
                eachOrder = "";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public void addOrder(int activityID, int customerID, LocalDateTime time) {
        PreparedStatement statement = null;
        String sql = "INSERT INTO activitiesdb.order (orderID, activityID, customerID, timeBought) " +
                "VALUES (?,?,?,?);";
        try {
            int id = Database.getMaxID("order", "orderID");
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, activityID);
            statement.setInt(3, customerID);
            statement.setTimestamp(4, Timestamp.valueOf(time));

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}