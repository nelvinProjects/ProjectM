package Server;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.setupDB();
        System.out.println("CREATING ACCOUNT");
        Login login = new Login();
        login.createAccount("tester@gmail.com", "123456", false, "Bob",
                "Jones", "MN1 7YP");
        System.out.println(login.checkAccountDetails("tester@gmail.com", "123456"));
        database.closeConnection();
    }
}
