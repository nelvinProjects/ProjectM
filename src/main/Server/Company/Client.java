package Server.Company;

public class Client {
    private int clientID;
    private String name, postcode;
    //TODO: remove postcode
    public Client(int clientID, String name, String postcode) {
        this.clientID = clientID;
        this.name = name;
        this.postcode = postcode;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
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