package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditGroupCommand.EditGroupDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

public class EditGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Group firstGroup = model.getFilteredGroupList().get(0);
        Group editedGroup = new GroupBuilder().build();

        EditGroupDescriptor descriptor = new EditGroupDescriptor(editedGroup).bu

    }
}
