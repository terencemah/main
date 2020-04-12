package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Time;

public class EventTest {

    private Event firstEvent = new Event("something", "somewhere", new Time(11, 11));
    private Event secondEvent = new Event("something", "somewhere", new Time(11, 11));

    @Test
    public void equals() {

        //different EventId -> return false
        assertFalse(firstEvent.equals(secondEvent));

        //same Event -> return true
        assertTrue(firstEvent.equals(firstEvent));
        assertTrue(secondEvent.equals(secondEvent));
    }

    @Test
    public void isSameEvent() {
        assertTrue(firstEvent.isSameEvent(firstEvent));
        assertFalse(firstEvent.isSameEvent(secondEvent));
    }

    @Test
    public void sameTime() {
        Event thirdEvent = new Event("something", "somewhere", new Time(22, 22));
        thirdEvent.setTime(new Time(11, 11));
        assertTrue(thirdEvent.getTime().equals(new Time(11, 11)));
    }

    @Test
    public void toString_success() {
        firstEvent.toString().equals("Event: something place: somewhere for 11h 11m");
    }
}
