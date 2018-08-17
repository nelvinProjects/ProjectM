package Tests.BookingTest;

import Server.Booking.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(1, 2, 3, 9.99, LocalDateTime.of(2018,
                05, 17, 16, 04));
    }

    @Test
    void getOrderID() {
        assertEquals(1, order.getOrderID(), "Order ID not match");
    }

    @Test
    void setOrderID() {
        order.setOrderID(5);
        assertEquals(5, order.getOrderID(), "Order ID not match");
    }

    @Test
    void getCustomerID() {
        assertEquals(2, order.getCustomerID(), "Customer ID not match");
    }

    @Test
    void setCustomerID() {
        order.setCustomerID(7);
        assertEquals(7, order.getCustomerID(), "Customer ID not match");
    }

    @Test
    void getActivityID() {
        assertEquals(3, order.getActivityID(), "Activity ID not match");
    }

    @Test
    void setActivityID() {
        order.setActivityID(9);
        assertEquals(9, order.getActivityID(), "Activity ID not match");
    }

    @Test
    void getPrice() {
        assertEquals(9.99, order.getPrice(), "Price not match");
    }

    @Test
    void setPrice() {
        order.setPrice(19.99);
        assertEquals(19.99, order.getPrice(), "Price not match");
    }

    @Test
    void getOrderedAt() {
        assertEquals(LocalDateTime.of(2018, 05, 17, 16, 04),
                order.getOrderedAt(), "Time plus Date not match");
    }

    @Test
    void setOrderedAt() {
        order.setOrderedAt(LocalDateTime.of(2017, 06, 19, 19, 05));
        assertEquals(LocalDateTime.of(2017, 06, 19, 19, 05),
                order.getOrderedAt(), "Time plus Date not match");
    }
}