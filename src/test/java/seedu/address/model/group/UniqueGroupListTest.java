package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.exceptions.DuplicateGroupException;

public class UniqueGroupListTest {

    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void remove_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.removeGroup(null));
    }

    @Test
    public void contains_groupInList_returnsTrue() {
        Group group = new Group(new Name("Soc Friends"));
        uniqueGroupList.addGroup(group);
        assertTrue(uniqueGroupList.contains(group));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        Group group = new Group(new Name("Soc Friends"));
        uniqueGroupList.addGroup(group);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.addGroup(group));
    }

    @Test
    public void setGroups_nullUniqueGroupsList_throwsNullPointerException() {
        assertThrows(
                NullPointerException.class, () -> uniqueGroupList.setGroups((UniqueGroupList) null));
    }

}
