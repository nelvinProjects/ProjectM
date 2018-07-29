package Server.Activities;

import Server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Review {

    public ArrayList<String> retrieveReview(int activityID) {
        ArrayList<String> review = new ArrayList<>();
        String output = "";
        PreparedStatement statement;
        String sql = "SELECT customerID, reviewText FROM review WHERE activityID = ?";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, activityID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                output += rs.getString(1);
                output += ",";
                output += rs.getString(2);
                review.add(output);
                output = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return review;
    }

    public void addReview(int activityID, int customerID, String review) {
        if (reviewExist(activityID, customerID)) {
            PreparedStatement statement;
            int max = Database.getMaxID("review", "rID");
            String sql = "INSERT INTO review (rID, activityID, customerID, reviewText) VALUES " +
                    "(?,?,?,?)";
            try {
                statement = Database.dbConnection.prepareStatement(sql);
                statement.setInt(1, max);
                statement.setInt(2, activityID);
                statement.setInt(3, customerID);
                statement.setString(4, review);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean reviewExist(int activityID, int customerID) {
        PreparedStatement statement;
        String sql = "SELECT * FROM review WHERE activityID = ? AND customerID = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, activityID);
            statement.setInt(2, customerID);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
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