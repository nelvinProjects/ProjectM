package Server.Activities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Server.Connections.Database;

public class ActivityImages {

    //TODO: BLOB output --> To image in webpage
    //todo public img?
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
    public void addImages(int activityID, InputStream img){
    	PreparedStatement statement = null;
		String sql = "INSERT INTO activityimages (actImgID, activityID, image) values("
				+ "?,?,?);";
		try {
			statement = Database.dbConnection.prepareStatement(sql);
			int id = Database.getMaxID("activityimages", "actImgID");
			statement = Database.dbConnection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setInt(2, activityID);
			if (img !=  null) {
//				InputStream binImg = new BufferedReader(new FileInputStream(img))
				statement.setBinaryStream(3, img);
				statement.execute();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}

    }
}
