package Server.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Server.Connections.Database;

public class Follow {

    public String retrieveFollows(int customerID) {
        String follows = "";
        PreparedStatement statement;
        String sql = "SELECT clientID FROM follow WHERE customerID = ?";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, customerID);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            String comma = "";
            while (rs.next()) {
                if (count > 0) comma = ",";
                follows += comma + rs.getInt(1);
                count++;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return follows;
    }

    public void addFollow(int clientID, int customerID) {
        if (followExist(clientID, customerID)) {
            PreparedStatement statement = null;
            String sql = "INSERT into follow (customerID, clientID) values " +
                    "(?,?)";
            try {
                statement = Database.dbConnection.prepareStatement(sql);
                statement.setInt(1, customerID);
                statement.setInt(2, clientID);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean followExist(int clientID, int customerID) {
        PreparedStatement statement;
        String sql = "SELECT * FROM follow WHERE customerID = ? AND clientID = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, customerID);
            statement.setInt(2, clientID);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) count++;
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
