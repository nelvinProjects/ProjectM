package Server;

import java.sql.*;

public class Database {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";

    static final String USER = "root";
    static final String PASS = "1313";

    public static Connection dbConnection;

    public void setupDB() {
        try {
            Class.forName(JDBC_DRIVER);
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getMaxID(String tableName){
        int max = 0;
        PreparedStatement statement;
        String getsql = "SELECT MAX(id) from ?";
        try {
            statement = Database.dbConnection.prepareStatement(getsql);
            statement.setString(1, tableName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) max = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        max++;
        return max;
    }

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}