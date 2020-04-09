package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.ModelStub;

public class AddGroupCommandTest {
    private static final Logger logger = LogsCenter.getLogger(AddGroupCommandTest.class);

    @Test
    public void constructor_nullGroup_throwsNullGroupPointer() {
        assertThrows(NullPointerException.class, () -> new AddGroupCommand(null));
    }

    @Test
    public void execute_groupAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingGroupAdded modelStub = new ModelStubAcceptingGroupAdded();
        Group validGroup = new GroupBuilder().build();
        validGroup.setMemberIDs(new ArrayList<>(Arrays.asList(1, 2, 3)));
        CommandResult commandResult = new AddGroupCommand(validGroup).execute(modelStub);

        assertEquals(String.format(AddGroupCommand.MESSAGE_SUCCESS, validGroup), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validGroup), modelStub.groupsAdded);

    }

    private class ModelStubWithGroup extends ModelStub {
        private final Group group;

        ModelStubWithGroup(Group group) {
            requireNonNull(group);
            this.group = group;
        }

        @Override
        public boolean hasGroup(Group group) {
            requireNonNull(group);
            return this.group.equals(group);
        }
    }


    private class ModelStubAcceptingGroupAdded extends ModelStub {
        final ArrayList<Group> groupsAdded = new ArrayList<>();

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            // persons list with sample persons
            UniquePersonList uniquePersonList = new UniquePersonList();
            uniquePersonList.add(ALICE);
            uniquePersonList.add(BENSON);
            uniquePersonList.add(CARL);
            FilteredList<Person> sampleList = new FilteredList<>(uniquePersonList.asUnmodifiableObservableList());
            return sampleList;
        }

        @Override
        public boolean hasGroup(Group toCheck) {
            requireNonNull(toCheck);
            return groupsAdded.stream().anyMatch(toCheck::equals);
        }

        @Override
        public void addGroup(Group toAdd) {
            requireNonNull(toAdd);
            groupsAdded.add(toAdd);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
