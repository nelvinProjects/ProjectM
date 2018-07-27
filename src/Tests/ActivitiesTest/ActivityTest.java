package Tests.ActivitiesTest;

import Server.Activities.Activity;
import Server.Activities.ActivityBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    private Activity activity;

    @BeforeEach
    public void setUp() {
        TreeSet<String> tempTags = new TreeSet<>();
        tempTags.add("Cycling");
        tempTags.add("Swimming");
        activity = new ActivityBuilder().setID(1)
                .setClient(1)
                .activityDesc("Test Test Test bye")
                .date(LocalDate.of(2018, 03, 01))
                .time(LocalTime.of(12, 56))
                .preferences(tempTags)
                .activityLive(true)
                .advert(true)
                .streetAddress1("19 King Street")
                .city("Manchester")
                .county("Greater Manchester")
                .postcode("MA1 1NB")
                .build();
    }

    @Test
    public void getActivityID() {
        assertEquals(1, activity.getActivityID(), "ID Not match");
    }

    @Test
    void setActivityID() {
        activity.setActivityID(2);
        assertEquals(2, activity.getActivityID(), "ID Not Match");
    }

    @Test
    void getClientID() {
        assertEquals(1, activity.getClientID(), "ID Not Match");
    }

    @Test
    void setClientID() {
        activity.setClientID(2);
        assertEquals(2, activity.getClientID(), "ID Not Match");
    }

    @Test
    void getDescription() {
        assertEquals("Test Test Test bye", activity.getDescription(),
                "Description not match");
    }

    @Test
    void setDescription() {
        activity.setDescription("Nothing yet to add");
        assertEquals("Nothing yet to add", activity.getDescription(),
                "Description not match");
    }

    @Test
    void isActive() {
        assertEquals(true, activity.isActive(), "Active not match");
    }

    @Test
    void setActive() {
        activity.setActive(false);
        assertEquals(false, activity.isActive(), "Active not match");
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.of(2018, 03, 01), activity.getDate(),
                "Date not match");
    }

    @Test
    void setDate() {
        activity.setDate(LocalDate.of(2018, 05, 12));
        assertEquals(LocalDate.of(2018, 05, 12), activity.getDate(),
                "Date not match");
    }

    @Test
    void getTime() {
        assertEquals(LocalTime.of(12, 56), activity.getTime(),
                "Time not match");
    }

    @Test
    void setTime() {
        activity.setTime(LocalTime.of(15, 14));
        assertEquals(LocalTime.of(15, 14), activity.getTime(),
                "Time not match");
    }

    @Test
    void isAdvert() {
        assertEquals(true, activity.isAdvert(), "Advert not match");
    }

    @Test
    void setAdvert() {
        activity.setAdvert(false);
        assertEquals(false, activity.isAdvert(), "Advert not match");
    }

    @Test
    void getTags() {
        TreeSet<String> tempTags = new TreeSet<>();
        tempTags.add("Cycling");
        tempTags.add("Swimming");
        assertEquals(tempTags, activity.getTags(), "Tags not match");
    }

    @Test
    void setTags() {
        TreeSet<String> tempTags = new TreeSet<>();
        tempTags.add("Go kart");
        tempTags.add("Swimming");
        activity.setTags(tempTags);
        assertEquals(tempTags, activity.getTags(), "Tags not match");
    }

    @Test
    void getAddress1() {
        assertEquals("19 King Street", activity.getStreetAddress1(), "Street not match");

    }

    @Test
    void setAddress1() {
        activity.setStreetAddress1("3 Bridge Road");
        assertEquals("3 Bridge Road", activity.getStreetAddress1(), "Street not match");
    }

    @Test
    void getSetAddress2() {
        assertEquals(null, activity.getStreetAddress2(), "Street not match");
        activity.setStreetAddress2("RiverEdge");
        assertEquals("RiverEdge", activity.getStreetAddress2(), "Street not match");
    }

    @Test
    void getSetCityTest() {
        assertEquals("Manchester", activity.getCity(), "City not match");
        activity.setCity("Liverpool");
        assertEquals("Liverpool", activity.getCity(), "City not match");
    }

    @Test
    void getSetCounty() {
        assertEquals("Greater Manchester", activity.getCounty(), "County not match");
        activity.setCounty("Essex");
        assertEquals("Essex", activity.getCounty(), "County not match");
    }

    @Test
    void getSetPostcode() {
        assertEquals("MA1 1NB", activity.getPostcode(), "Postcode not match");
        activity.setPostcode("LN1 1JB");
        assertEquals("LN1 1JB", activity.getPostcode(), "Postcode not match");
    }
}