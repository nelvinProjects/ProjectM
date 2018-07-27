package Server.Company;

import Server.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Follow {

    public String retrieveFollows(int customerID){
        String friends = "";
        PreparedStatement statement;
        String sql = "SELECT friendID FROM Friends WHERE customerID = ?";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1,customerID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                friends += rs.getInt(1);
                friends += ",";
            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return friends;
    }

    public void addFollow(int friendID, int customerID){

    }
}
