package Server;

import Server.Company.Client;
import Server.User.Chat;
import Server.User.Customer;
import Server.User.Friend;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.setupDB();

        System.out.println("CREATING ACCOUNT");
        Login login = new Login();
        login.createAccount("test@gmail.com", "123456", false, "Bib",
                "Jon", LocalDate.of(1995, 05, 05), "MN1 7YP");
        System.out.println("DUPLICATE ENTRY TO CREATE ACCOUNT");
        login.createAccount("tester@gmail.com", "123456", false, "Bob",
                "Jones", LocalDate.of(1994, 05, 05), "MN1 7YP");
        System.out.println("NEW ACCOUNTS");
        login.createAccount("tester1@gmail.com", "123456", false, "Tim",
                "Wood", LocalDate.of(1992, 05, 05), "MN6 2WP");
        login.createAccount("tester6@gmail.com", "123456", false, "Sim",
                "Rook", LocalDate.of(1996, 05, 05), "MN5 2SP");
        login.createAccount("tester9@gmail.com", "123456", false, "Sam",
                "None", LocalDate.of(1999, 05, 05), "MN6 2VB");
        System.out.println("CREATING CLIENTS");
        login.createAccount("company@gmail.com", "123456", true, "Go-Kart ltd",
                null, null, "MN1 0YY");

        System.out.println();
        System.out.println("LOGIN CUSTOMER");
        String[] customerData = login.checkAccountDetails("tester@gmail.com", "123456");
        System.out.println(Arrays.toString(customerData));
        System.out.println("LOGIN CLIENT");
        String[] clientData = login.checkAccountDetails("company@gmail.com", "123456");
        System.out.println(Arrays.toString(clientData));

        System.out.println();
        System.out.println("CREATE CUSTOMER");
        int age = Period.between(LocalDate.parse(customerData[2]), LocalDate.now()).getYears();
        Customer customer = new Customer(Integer.valueOf(customerData[0]), age, customerData[1], customerData[3], null);
        System.out.println("CUSTOMER NAME " + customer.getName() + " CUSTOMER AGE " + customer.getAge());

        System.out.println();
        System.out.println("CREATE CLIENT");
        Client client = new Client(Integer.valueOf(clientData[0]), clientData[1], clientData[2]);
        System.out.println("CLIENT ID " + client.getClientID() + " CLIENT NAME " + client.getName());

        System.out.println();
        System.out.println("ADD FRIEND");
        Friend friend = new Friend();
        friend.addFriends(1, 2);
        System.out.println("DUPLICATE FRIEND ENTRY");
        friend.addFriends(1, 2);
        System.out.println("FEW FRIENDS");
        friend.addFriends(1, 3);
        friend.addFriends(3, 4);
        System.out.println();

        System.out.println("RETRIEVE FRIEND");
        System.out.println(friend.retrieveFriends(1));
        System.out.println();

        System.out.println("ADD CHAT");
        Chat chat = new Chat();
//        chat.addChat(1,2,"Hi", 1);
//        chat.addChat(1,2,"Hi", 2);
//        chat.addChat(2,1,"How are you?", 2);
//        chat.addChat(1,3,"Message test", 3);
//        chat.addChat(3,1,"bye", 2);
        System.out.println(chat.retrieveChat(1, 2));
        System.out.println(chat.retrieveChat(2, 1));
        database.closeConnection();
    }
}
