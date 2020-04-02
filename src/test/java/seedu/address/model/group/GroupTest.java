package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.ActivityList;
import seedu.address.model.person.Name;
import seedu.address.model.person.PlaceList;

public class GroupTest {

    @Test
    public void newGroupTest() {
        assertEquals((new Group(new Name("John Doe"), new PlaceList(new ArrayList<String>()),
                new ActivityList(new ArrayList<String>()))).getName().fullName, "John Doe");
    }
}
