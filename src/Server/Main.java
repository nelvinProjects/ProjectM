package Server;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.setupDB();
        database.closeConnection();
    }
}
