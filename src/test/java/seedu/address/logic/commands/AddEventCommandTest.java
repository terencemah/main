package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Time;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddEventCommand.
 */
public class AddEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addEventUnfilteredList_success() {
        final String activity = "test";
        final String place = "anywhere";
        final Time time = new Time(30, 0);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withName(firstPerson.getName().fullName).withTime("030").withActivityList("test, test")
                .withPlaceList("anywhere, anywhere").build();

        AddEventCommand addEventCommand = new AddEventCommand(activity, INDEX_FIRST_PERSON.getOneBased(), place, time);

        String expectedMessage = String.format(AddEventCommand.MESSAGE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addEventFilteredList_success() {
        final String activity = "test";
        final String place = "anywhere";
        final Time time = new Time(30, 0);

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withName(firstPerson.getName().fullName).withTime("030").withActivityList("test")
                .withPlaceList("anywhere").build();

        AddEventCommand addEventCommand = new AddEventCommand(activity, INDEX_FIRST_PERSON.getOneBased(), place, time);

        String expectedMessage = String.format(AddEventCommand.MESSAGE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        final String activity = "test";
        final String place = "anywhere";
        final Time time = new Time(30, 0);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        AddEventCommand addEventCommand = new AddEventCommand(activity, outOfBoundIndex.getOneBased(), place, time);

        assertCommandFailure(addEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        final String activity = "test";
        final String place = "anywhere";
        final Time time = new Time(30, 0);
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        AddEventCommand addEventCommand = new AddEventCommand(activity, outOfBoundIndex.getOneBased(), place, time);

        assertCommandFailure(addEventCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addEventOfMoreThan1HourUnfilteredList_success() {
        final String activity = "test";
        final String place = "anywhere";
        final Time time = new Time(1, 01);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withName(firstPerson.getName().fullName).withTime("101").withActivityList("test, test")
                .withPlaceList("anywhere, anywhere").build();

        AddEventCommand addEventCommand = new AddEventCommand(activity, INDEX_FIRST_PERSON.getOneBased(), place, time);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        String expectedMessage = String.format(AddEventCommand.MESSAGE_SUCCESS, editedPerson);

        assertCommandSuccess(addEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final String activity = "test";
        final int index = 1;
        final String place = "anywhere";
        final Time time = new Time(30, 0);

        final AddEventCommand standardCommand = new AddEventCommand(activity, index, place, time);

        AddEventCommand commandWithSameValues = new AddEventCommand("test", 1, "anywhere",
                new Time(30, 0));

        //same values -> returns true
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddEventCommand(activity, 2, place, time)));

        // different place -> returns false
        assertFalse(standardCommand.equals(new AddEventCommand(activity, index, "anywhere 2", time)));

        // different activity -> returns false
        assertFalse(standardCommand.equals(new AddEventCommand("test 2", index, place, time)));

        // different time -> returns false
        assertFalse(standardCommand.equals(new AddEventCommand(activity, index, place, new Time(45,
                0))));
    }
}
