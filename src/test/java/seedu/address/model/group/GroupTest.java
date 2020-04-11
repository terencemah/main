package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.TypicalGroups;

public class GroupTest {

    @Test
    public void equals() {
        Group soc = TypicalGroups.SOC;
        Group rc = TypicalGroups.RC;

        //same object -> true
        assertTrue(soc.equals(soc));

        //same values -> true
        Group socCopy = new GroupBuilder().build();
        assertTrue(soc.equals(socCopy));

        // different values -> false
        assertFalse(soc.equals(rc));
    }
}
