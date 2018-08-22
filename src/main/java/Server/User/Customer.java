package Server.User;

import java.util.TreeSet;

public class Customer {
    int customerID, age;
    String name, postcode;
//    TreeSet<String> preferences;

    public Customer(int customerID, int age, String name, String postcode){
        this.customerID = customerID;
        this.name = name;
        this.age = age;
        this.postcode = postcode;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
