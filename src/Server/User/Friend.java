package Server.User;

import Server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Friend {
    public String retrieveFriends(int customerID) {
        String friends = "";
        PreparedStatement statement;
        String sql = "SELECT * FROM friend WHERE customerIDO = ? OR customerIDT = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, customerID);
            statement.setInt(2, customerID);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            String comma = "";
            while (rs.next()) {
                if (count > 0) comma = ",";
                if (rs.getInt(1) == customerID) {
                    friends += comma + rs.getInt(2);
                } else {
                    friends += comma + rs.getInt(1);
                }
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friends;
    }

    public void addFriends(int customerID1, int customerID2) {
        if (friendExist(customerID1, customerID2)) {
            PreparedStatement statement = null;
            String sql = "INSERT into friend (customerIDO, customerIDT) values " +
                    "(?,?)";
            try {
                statement = Database.dbConnection.prepareStatement(sql);
                statement.setInt(1, customerID1);
                statement.setInt(2, customerID2);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean friendExist(int friend1, int friend2) {
        PreparedStatement statement;
        String sql = "SELECT * FROM friend WHERE customerIDO = ? AND customerIDT = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, friend1);
            statement.setInt(2, friend2);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            System.out.println("Friend COUNT " + count);
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
