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

    @Test
    public void timeCreationInvalid() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, (
        ) -> new Time(162, 2));
        Assertions.assertEquals(exception.getMessage(), MINUTES_RANGE_ERROR);
    }

    @Test
    public void timeEqualsFalse() {
        Time time1 = new Time(59, 2);
        Time time2 = new Time(0, 0);
        Assertions.assertFalse(time1.equals(time2));
    }

    @Test
    public void timeHashCodeDifferent() {
        Time time1 = new Time(59, 0);
        Time time2 = new Time(1, 0);
        Assertions.assertFalse(time1.hashCode() == time2.hashCode());
    }

    @Test
    public void timeToString() {
        Time time = new Time(59, 2);
        Assertions.assertEquals("2h 59m", time.toString());
    }

    @Test
    public void setMinutesRangeError() {
        Time time = new Time(0, 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            time.setMinutes(120);
        });
    }

    @Test
    public void setMinutesSuccessfully() {
        Time time = new Time(1, 0);
        time.setMinutes(2);
        Assertions.assertEquals(2, time.getMinutes());
    }

    @Test
    public void setHoursRangeError() {
        Time time = new Time(30, 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            time.setHours(-1);
        });
    }

}
