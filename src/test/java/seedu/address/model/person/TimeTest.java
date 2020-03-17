package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void equals() {
        Time time = new Time(0);

        // same object -> returns true
        assertTrue(time.equals(time));

        // same values -> returns true
        Time timeCopy = new Time(time.value);
        assertTrue(time.equals(timeCopy));

        // different types -> returns false
        assertFalse(time.equals(1));

        // null -> returns false
        assertFalse(time.equals(null));

        // different time -> returns false
        Time differentTime = new Time(0);
        assertFalse(time.equals(differentTime));
    }
}