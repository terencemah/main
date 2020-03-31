package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.event.TempPlace;
import seedu.address.model.group.Group;
import seedu.address.model.person.EventDescriptor;
import seedu.address.model.person.Person;
//import seedu.address.ui.ObservablePlaceListPanel;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Group> filteredGroups;
    private final ObservableList<EventDescriptor> frequencyList;
    //private final ObservableList<TempPlace> placesList;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredGroups = new FilteredList<>(this.addressBook.getGroupList());
        frequencyList = FXCollections.observableArrayList();
        //placesList = FXCollections.observableArrayList();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // =========== AddressBook
    // ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return addressBook.hasGroup(group);
    }

    @Override
    public boolean hasPersons(List<Person> people) {
        requireNonNull(people);
        return addressBook.hasPersons(people);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addGroup(Group group) {
        addressBook.addGroup(group);
    }

    @Override
    public void deleteGroup(Group group) {
        addressBook.removeGroup(group);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    // =========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return this.filteredGroups;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    @Override
    public void importCsvToAddressBook(List<Person> importedPeople) throws CommandException {
        requireNonNull(importedPeople);
        addressBook.addPersons(importedPeople);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    public void showPlaceList(Person target) {
        copyList(target.getPlaceList());
    }

    public void showActivityList(Person target) {
        copyList(target.getActivityList());
    }

    /**
     * Copies the active PlaceList or ActivityList onto the Model's Frequency List.
     * @param list List to be copied.
     */
    private void copyList(ObservableList<EventDescriptor> list) {
        frequencyList.clear();
        for (EventDescriptor eventDescriptor : list) {
            frequencyList.add(eventDescriptor);
        }
    }

    /*public void showPlaces(Person p) {
        copyList2(p.getPlaceList2().getObservablePlaceList());
    }

    public void copyList2(ObservableList<TempPlace> xs) {
        for(TempPlace p : xs) {
            placesList.add(p);
        }
    }*/

    @Override
    public ObservableList<EventDescriptor> getFrequencyList() {
        return frequencyList;
    }

    /*@Override
    public ObservableList<TempPlace> getTempPlaceList() {
        return this.placesList;
    }*/

    @Override
    public String toString() {
        String finalContent = "";

        // NOTE: VersionedAddressBook does not have a toString() method, so you will need to manually
        // implement VersionedAddressBook#toString() to see its content!
        finalContent += "versioned addressbook: " + addressBook.toString();

        finalContent += ", filtered person list: [";
        for (Person p : filteredPersons) {
            finalContent += p.toString() + ",";
        }
        finalContent += "]";

        return finalContent;
    }
}

