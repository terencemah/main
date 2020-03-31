package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

public class ViewCommandTest {

    @Test
    public void equals() {
        ViewCommand firstViewCommand = new ViewCommand(INDEX_FIRST_PERSON,"places");
        ViewCommand secondViewCommand = new ViewCommand(INDEX_FIRST_PERSON,"recent");

        // same object -> returns true
        assertTrue(firstViewCommand.equals(firstViewCommand));

        // same values -> returns true
        ViewCommand firstViewCommandCopy = new ViewCommand(INDEX_FIRST_PERSON, "places");
        assertTrue(firstViewCommand.equals(firstViewCommandCopy));

        // different types -> returns false
        assertFalse(firstViewCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewCommand.equals(null));

        // different parameter -> returns false
        assertFalse(firstViewCommand.equals(secondViewCommand));
    }
}
