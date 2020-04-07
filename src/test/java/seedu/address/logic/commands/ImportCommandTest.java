package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATH;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ImportCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ImportCommand firstImportCommand = new ImportCommand("testing.csv", "testing2.csv", "testing2.csv");
        ImportCommand secondImportCommand = new ImportCommand("testing2.csv", "testing.csv", "testing2.csv");

        // same object -> returns true
        assertTrue(firstImportCommand.equals(firstImportCommand));

        // same values -> returns true
        ImportCommand firstImportCommandCopy = new ImportCommand("testing.csv", "testing2.csv", "testing2.csv");
        assertTrue(firstImportCommand.equals(firstImportCommandCopy));

        // different types -> returns false
        assertFalse(firstImportCommand.equals(1));

        // null -> returns false
        assertFalse(firstImportCommand.equals(null));

        // different file -> returns false
        assertFalse(firstImportCommand.equals(secondImportCommand));
    }

//    @Test
//    public void execute_importFile_notFound() {
//        String emptyFilePath = "";
//        ImportCommand importCommand = new ImportCommand(emptyFilePath, emptyFilePath, emptyFilePath);
//        String expectedMessage = MESSAGE_INVALID_PATH + "\n" + ImportCommand.MESSAGE_USAGE;
//        assertCommandFailure(importCommand, model, expectedMessage);
//    }

}
