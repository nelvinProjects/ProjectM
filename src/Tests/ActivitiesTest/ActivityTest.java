package Tests.ActivitiesTest;

import Server.Activities.Activity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    private Activity activity;

    @BeforeAll
    void setUp() {
        activity = new Activity();
    }

    @AfterAll
    void destroy(){
        activity = null;
    }
}