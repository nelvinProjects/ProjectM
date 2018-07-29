package Server.Company;

import Server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return follows;
    }

    public void addFollow(int clientID, int customerID) {
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
