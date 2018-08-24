package Server.Connections;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class Login {
    public String[] checkAccountDetails(String username, String password) {
        int id = 0;
        String passwordValue = "";
        boolean seller = false;
        String[] values = new String[2];
        PreparedStatement statement = null;
        String sql = "SELECT customerID, seller, password FROM login WHERE email = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setString(1, username.toLowerCase());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                seller = rs.getBoolean(2);
                passwordValue = rs.getString(3);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (BCrypt.checkpw(password, passwordValue)) {
			values[0] = String.valueOf(id);
        	values[1] = String.valueOf(seller);
        	return values;
		}else {
			String[] empty = new String[0];
			return empty;
		}       
    }

    public String[] getCustomerDetails(int id) {
        String[] details = new String[4];
        details[0] = String.valueOf(id);
        PreparedStatement statement = null;
        String sql = "SELECT * FROM customer WHERE customerID = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String tempJoin = "";
                tempJoin += rs.getString(2) + " ";
                tempJoin += rs.getString(3);
                details[1] = tempJoin;
                details[2] = String.valueOf(rs.getDate(4));
                details[3] = rs.getString(5);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public String[] getClientDetails(int id) {
        String[] details = new String[3];
        details[0] = String.valueOf(id);
        PreparedStatement statement = null;
        String sql = "SELECT * FROM company WHERE clientID = ?;";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                details[1] = rs.getString(2);
                details[2] = rs.getString(3);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public void createAccount(String email, String password, boolean client, String fname,
                              String sName, Date dob, String postcode) {
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
                if (client) {
                    createClient(id, fname);
                } else {
                    createCustomer(id, fname, sName, dob, postcode);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createCustomer(int id, String fname, String sname, Date dob, String postcode) {
        PreparedStatement statement = null;
        String sql = "INSERT into customer (customerID, fame, sname, dOb, postcode) values " +
                "(?,?,?,?,?)";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, fname);
            statement.setString(3, sname);
            statement.setDate(4, dob);
            statement.setString(5, postcode.toUpperCase());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createClient(int id, String name) {
        PreparedStatement statement = null;
        String sql = "INSERT into company (clientID, name) values " +
                "(?,?)";
        try {
            statement = Database.dbConnection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
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
            rs.close();
//            System.out.println("Account COUNT " + count);
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
