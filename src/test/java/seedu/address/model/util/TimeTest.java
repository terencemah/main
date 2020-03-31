package seedu.address.model.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Time;

public class TimeTest {

    public static final String MINUTES_RANGE_ERROR = "Minutes must be within 0-59 (inclusive).";

    @Test
    public void timeCreationValid() {
        Time time = new Time(59, 2);

        Assertions.assertTrue(time.getHours() == 2 && time.getMinutes() == 59);
    }

    @Test void timeCreationInvalid() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, (
                ) -> new Time(162, 2));
        Assertions.assertEquals(exception.getMessage(), MINUTES_RANGE_ERROR);
    }

    @Test void timeToString() {
        Time time = new Time(59, 2);
        Assertions.assertEquals("2h 59m", time.toString());
    }

}
