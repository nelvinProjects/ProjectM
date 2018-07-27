package Server.Activities;

import Server.Database;

import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityImages {

    //TODO: BLOB output --> To image in webpage
    public OutputStream retrieveImages(int activityID){
        Blob output = null;
        PreparedStatement statement;
        String sql = "SELECT Image FROM ActivityImages WHERE Id = ?";
        try{
           statement = Database.dbConnection.prepareStatement(sql);
           statement.setInt(1, activityID);
            ResultSet rs = statement.executeQuery();
           while (rs.next()){
               output = rs.getBlob("Image");
           }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    //TODO: Upload image into database --> image data to blob
    public void addImages(){

    }
}
