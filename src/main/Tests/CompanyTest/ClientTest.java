package Tests.CompanyTest;

import Server.Company.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1, "Aim Cim", "MA2 8TN");
    }

    @Test
    void getClientID() {
        assertEquals(1, client.getClientID(), "ID not match");
    }

    @Test
    void setClientID() {
        client.setClientID(5);
        assertEquals(5, client.getClientID(), "ID not match");
    }

    @Test
    void getName() {
        assertEquals("Aim Cim", client.getName(), "Name not match");
    }

    @Test
    void setName() {
        client.setName("Sim Vim");
        assertEquals("Sim Vim", client.getName(), "Name not match");
    }

    @Test
    void getPostcode() {
        assertEquals("MA2 8TN", client.getPostcode(), "Postcode not match");
    }

    @Test
    void setPostcode() {
        client.setPostcode("MA7 9OP");
        assertEquals("MA7 9OP", client.getPostcode(), "Postcode not match");
    }
}