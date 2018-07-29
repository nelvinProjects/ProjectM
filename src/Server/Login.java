package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    //    TODO: if seller redirect to client page
    public int checkAccountDetails(String username, String password) {
        int id = 0;
        PreparedStatement statement = null;
        String sql = "SELECT customerID FROM login WHERE email = ? && password = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setString(1, username.toLowerCase());
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void createAccount(String email, String password, boolean client, String fname,
                              String sName, String postcode) {
        if (checkAccountExist(email)) {
            PreparedStatement statement = null;
            String sql = "INSERT into login (customerID, email, password, seller) values " +
                    "(?,?,?,?)";
            try {
                int id = Database.getMaxID("login", "customerID");
                statement = Database.dbConnection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.setString(2, email.toLowerCase());
                statement.setString(3, password);
                statement.setBoolean(4, client);
                statement.execute();
                createCustomer(id, fname, sName, postcode);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createCustomer(int id, String fname, String sname, String postcode){
        PreparedStatement statement = null;
        String sql = "INSERT into customer (customerID, fName, sName, postcode) values " +
                "(?,?,?,?)";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, fname);
            statement.setString(3, sname);
            statement.setString(4, postcode.toUpperCase());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkAccountExist(String email) {
        PreparedStatement statement;
        String sql = "SELECT * FROM login WHERE email = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setString(1, email.toLowerCase());
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            System.out.println("COUNT " + count);
            if (count > 0) return false;
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void resetPassword(String email) {
        //TODO send email with code randomly created
        //TODO check if code match in page, if does allow reset password?
    }
}
