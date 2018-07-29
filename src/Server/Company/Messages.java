package Server.Company;

import Server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Messages {
    public String retrieveMessages(int clientID, int customerID) {
        String message = "";
        PreparedStatement statement;
        String sql = "SELECT message, sender FROM companymessage WHERE customerID = ? AND clientID = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, customerID);
            statement.setInt(2, clientID);

            ResultSet rs = statement.executeQuery();
            int count = 0;
            String comma = "";
            while (rs.next()) {
                if (count > 0) comma = ",";
                message += comma + rs.getString(1);
                message += "|" + rs.getInt(2);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public void addMessages(int clientID, int customerID, String message, int sender) {
        PreparedStatement statement = null;
        String sql = "INSERT into companymessage (mID,customerID ,clientID, message, sender) values " +
                "(?,?,?,?,?)";
        try {
            int id = Database.getMaxID("companymessage", "mID");
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, customerID);
            statement.setInt(3, clientID);
            statement.setString(4, message);
            statement.setInt(5, sender);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
