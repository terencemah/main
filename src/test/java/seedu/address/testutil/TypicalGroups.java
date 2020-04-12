package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * A utility class containing list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group SOC = new GroupBuilder()
            .withName("SoC Friends").withId(1).withTime("1h 1m").build();
    public static final Group RC = new GroupBuilder().withName("RC Friends").withId(2).withTime("2h 2m").build();
    public static final Group NS = new GroupBuilder().withName("NS Friends").withId(3).withTime("0h 30m").build();

    private TypicalGroups() {
    }

    /**
     * Returns an {@code AddressBook} with all the typical groups
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(SOC, RC, NS));
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL));
    }
}
