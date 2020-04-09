package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //    @Test
    //    public void execute_validIndexUnfilteredList_success() {
    //        Group groupToDelete = model.getFilteredGroupList().get(0);
    //        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(Index.fromZeroBased(1));
    //        String expectedMessage = String.format(DeleteGroupCommand.MESSAGE_DELETE_PERSON_SUCCESS, groupToDelete);
    //
    //        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    //        expectedModel.deleteGroup(groupToDelete);
    //
    //        assertCommandSuccess(deleteGroupCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(outOfBoundIndex);

        assertCommandFailure(deleteGroupCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }


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
