package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditGroupCommand.EditGroupDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.testutil.EditGroupDescriptorBuilder;
import seedu.address.testutil.GroupBuilder;
public class EditGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Group editedGroup = new GroupBuilder().build();
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        ArrayList<Integer> newMemberIds = new ArrayList<>(Arrays.asList(1, 2, 3));
        Group firstGroup = model.getFilteredGroupList().get(0);
        editedGroup.setMemberIDs(newMemberIds);
        editedGroup.setName(new Name("new name"));

        EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder(editedGroup).build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(Index.fromOneBased(1), descriptor);

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setGroup(firstGroup, editedGroup);

        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Group anotherGroup = model.getFilteredGroupList().get(2);
        editedGroup.setName(new Name("only changing the name field"));

        EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder(editedGroup).build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(Index.fromOneBased(1), descriptor);

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setGroup(anotherGroup, editedGroup);

        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditGroupCommand editGroupCommand = new EditGroupCommand(Index.fromOneBased(1), new EditGroupDescriptor());
        Group editedGroup = model.getFilteredGroupList().get(0);

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showGroupAtIndex(model, Index.fromOneBased(1));

        Group secondGroup = model.getFilteredGroupList().get(0);
        editedGroup.setName(new Name("CS2103"));

        EditGroupCommand editGroupCommand = new EditGroupCommand(Index.fromOneBased(1),
                new EditGroupDescriptorBuilder().withName("CS2103").build());

        String expectedMessage = String.format(EditGroupCommand.MESSAGE_EDIT_GROUP_SUCCESS, editedGroup);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setGroup(secondGroup, editedGroup);
        assertCommandSuccess(editGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateGroupUnfilteredList_failure() {
        Group firstGroup = model.getFilteredGroupList().get(0);
        EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder(firstGroup).build();
        EditGroupCommand editGroupCommand = new EditGroupCommand(Index.fromOneBased(2), descriptor);

        assertCommandFailure(editGroupCommand, model, EditGroupCommand.MESSAGE_DUPLICATE_GROUP);
    }

}
