package Server.User;

import Server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Chat {
    public String retrieveChat(int customerID1, int customerID2) {
        String chats = "";
        PreparedStatement statement;
        String sql = "SELECT message, sender FROM chat WHERE customerIDO = ? AND customerIDT = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            if (customerID1 < customerID2) {
                statement.setInt(1, customerID1);
                statement.setInt(2, customerID2);
            } else {
                statement.setInt(1, customerID2);
                statement.setInt(2, customerID1);
            }
            ResultSet rs = statement.executeQuery();
            int count = 0;
            String comma = "";
            while (rs.next()) {
                if (count > 0) comma = ",";
                chats += comma + rs.getString(1);
                chats += "|" + rs.getInt(2);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }

    public void addChat(int customerID1, int customerID2, String message, int sender) {
        PreparedStatement statement = null;
        String sql = "INSERT into chat (chatID,customerIDO ,customerIDT, message, sender) values " +
                "(?,?,?,?,?)";
        try {
            int id = Database.getMaxID("chat", "chatID");
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            if (customerID1 < customerID2) {
                statement.setInt(2, customerID1);
                statement.setInt(3, customerID2);
            } else {
                statement.setInt(2, customerID2);
                statement.setInt(3, customerID1);
            }
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
