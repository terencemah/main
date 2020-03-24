package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class DeleteGroupCommandTest {

    @Test
    public void equals() {
        DeleteGroupCommand deleteGroupCommand1 = new DeleteGroupCommand(Index.fromZeroBased(0));
        DeleteGroupCommand deleteGroupCommand2 = new DeleteGroupCommand(Index.fromZeroBased(1));

        //check equals method
        assertTrue(deleteGroupCommand1.equals(deleteGroupCommand1));

        // different types -> returns false
        assertFalse(deleteGroupCommand1.equals(1));

        // different groups -> returns false
        assertFalse(deleteGroupCommand1.equals(deleteGroupCommand2));
    }
}
