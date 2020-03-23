package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

public class GroupTest {

    @Test
    public void newGroupTest() {
        assertEquals((new Group(new Name("John Doe"))).getName().fullName, "John Doe");
    }
}
