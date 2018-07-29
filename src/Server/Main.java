package Server;

import Server.User.Friend;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.setupDB();
        System.out.println("CREATING ACCOUNT");
        Login login = new Login();
        login.createAccount("tester@gmail.com", "123456", false, "Bob",
                "Jones", "MN1 7YP");
        System.out.println("DUPLICATE ENTRY TO CREATE ACCOUNT");
        login.createAccount("tester@gmail.com", "123456", false, "Bob",
                "Jones", "MN1 7YP");
        System.out.println("NEW ACCOUNTS");
        login.createAccount("tester1@gmail.com", "123456", false, "Tim",
                "Wood", "MN6 2WP");
        login.createAccount("tester6@gmail.com", "123456", false, "Sim",
                "Rook", "MN5 2SP");
        login.createAccount("tester9@gmail.com", "123456", false, "Sam",
                "None", "MN6 2VB");

        System.out.println("Login");
        System.out.println(login.checkAccountDetails("tester@gmail.com", "123456"));

        System.out.println("ADD FRIEND");
        Friend friend = new Friend();
        friend.addFriends(1,2);
        System.out.println("DUPLICATE FRIEND ENTRY");
        friend.addFriends(1,2);
        System.out.println("FEW FRIENDS");
        friend.addFriends(1,3);
        friend.addFriends(3,4);

        System.out.println("RETRIEVE FRIEND");
        System.out.println(friend.retrieveFriends(1));

        database.closeConnection();
    }
}
