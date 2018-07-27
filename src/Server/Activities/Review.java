package Server.Activities;

import Server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review {

    public String retrieveReview(int activityID) {
        String output = "";
        PreparedStatement statement;
        String sql = "SELECT customerID, review FROM Reviews WHERE activityID = ?";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, activityID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                output += rs.getString("customerID");
                output += ",";
                output += rs.getString("review");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public void addReview(int activityID, int customerID, String review) {
//        int max = 0;
        PreparedStatement statement;
//        String getsql = "SELECT MAX(id) from Reviews";
//        try {
//            statement = Database.dbConnection.prepareStatement(getsql);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) max = rs.getInt(1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        max++;
        int max = Database.getMaxID("Reviews");

        String sql = "INSERT into Reviews (id, activityID, customerID, review) values " +
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