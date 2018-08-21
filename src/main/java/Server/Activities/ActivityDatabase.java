package Server.Activities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Server.Connections.Database;


public class ActivityDatabase {
    public ArrayList<String> retrieveActivity() {
        ArrayList<String> act = new ArrayList<>();
        String activity = "";
        Statement statement;
        String sql = "SELECT * FROM activities INNER JOIN address ON activities.postcode = address.postcode;";
        try {
            statement = Database.dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                activity += rs.getInt(1);
                activity += "," + rs.getInt(2);
                activity += "," + rs.getString(3);
                activity += "," + rs.getString(4);
                activity += "," + rs.getDouble(5);
                activity += "," + rs.getBoolean(6);
                activity += "," + rs.getDate(7);
                activity += "," + rs.getTime(8);
                activity += "," + rs.getBoolean(9);
                activity += "," + rs.getString(10);
                activity += "," + rs.getInt(11);
                activity += "," + rs.getString(12);
                activity += "," + rs.getString(14);
                activity += "," + rs.getString(15);
                activity += "," + rs.getString(16);
                act.add(activity);
                activity = "";
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return act;
    }

    public void addActivity(int clientID, String title, int quantity, String description, double price, boolean AD,
                            boolean active, String address1, String address2, String city,
                            String postcode, LocalDate date, LocalTime time) {
        if (checkActivityExist(clientID, title)) {
            PreparedStatement statement = null;
            String sql = "INSERT INTO activities (activityID, clientID, title, description,price," +
                    "AD, date, time, active, address1, quantity, postcode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
                statement.setBoolean(6, AD);
                statement.setDate(7, Date.valueOf(date));
                statement.setTime(8, Time.valueOf(time));
                statement.setBoolean(9, active);
                statement.setString(10, address1);
                statement.setInt(11, quantity);
                statement.setString(12, postcode);

                statement.execute();
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
            if (count > 0) return false;
            else return true;
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
            if (count > 0) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
