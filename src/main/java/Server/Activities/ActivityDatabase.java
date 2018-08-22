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
                activity += "," + rs.getInt(6);
                activity += "," + rs.getBoolean(7);
                activity += "," + rs.getDate(8);
                activity += "," + rs.getTime(9);
                activity += "," + rs.getBoolean(10);
                activity += "," + rs.getString(11);
                activity += "," + rs.getInt(12);
                activity += "," + rs.getString(13);
                activity += "," + rs.getString(15);
                activity += "," + rs.getString(16);
                activity += "," + rs.getString(17);
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

    public void addActivity(Integer clientID, String title, int quantity, int age, String description, double price, boolean AD,
                            boolean active, String address1, String address2, String city,
                            String postcode, Date date, Time time) {
        if (checkActivityExist(clientID, title)) {
            PreparedStatement statement = null;
            String sql = "INSERT INTO activities (activityID, clientID, title, description,price," +
                    "age, AD, date, time, active, address1, quantity, postcode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                statement.setTime(9, time);
                statement.setBoolean(10, active);
                statement.setString(11, address1);
                statement.setInt(12, quantity);
                statement.setString(13, postcode);

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
