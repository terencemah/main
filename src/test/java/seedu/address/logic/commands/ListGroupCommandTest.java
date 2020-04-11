package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListGroupCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void initialSetup() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult commandResult = new CommandResult(ListGroupCommand.MESSAGE_SUCCESS, ViewType.GROUPS);
        assertCommandSuccess(new ListGroupCommand(), model, commandResult, expectedModel);
    }
}
