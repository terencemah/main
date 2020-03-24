package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Time;

/**
 * Represents the command to add a new event to CoderLifeInsights.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an event with a group or an individual"
            + "that adds an activity, place and time to the subject. \n"
            + "Parameters: [ACTIVITY] "
            + "["
            + PREFIX_PLACE
            + "PLACE] "
            + "["
            + PREFIX_MEMBER
            + "/"
            + PREFIX_GROUP
            + "INDEX] "
            + "["
            + PREFIX_TIME
            + "TIME] \n"
            + "Example: "
            + COMMAND_WORD
            + " Dancing "
            + PREFIX_MEMBER
            + "1 "
            + PREFIX_PLACE
            + "SCAPE "
            + PREFIX_TIME
            + "300";

    public static final String MESSAGE_INVALID_TIME_INPUT = "Time parameter needs to be at least 2 digits.\n"
            + "For example: "
            + "[5 minutes = 05]; "
            + "[1 hour = 100]; "
            + "[10 hours and 30 minutes = 1030]";
    public static final String MESSAGE_SUCCESS = "New event successfully added.";

    public static final String MESSAGE_ARGUMENTS = "Activity: %1$s, Index: %2$d, Place: %3$s, Time: %4$s";

    private final String activity;
    private final int index;
    private final String place;
    private final Time time;

    /**
     * @param activity to be added to person/group activity list
     * @param index of the person or group to be added to
     * @param place to be added to person/group place list
     * @param time to be added to person/group
     */
    public AddEventCommand(String activity, int index, String place, Time time) {
        requireAllNonNull(activity, index, place, time);

        this.activity = activity;
        this.index = index;
        this.place = place;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index - 1 >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index - 1);
        Time current = personToEdit.getTime();
        int newMins = 0;
        int newHours = 0;
        if (current.getMinutes() + time.getMinutes() >= 60) {
            newMins = (current.getMinutes() + time.getMinutes()) - 60;
            newHours = (current.getHours() + time.getHours()) + 1;
        } else {
            newMins = (current.getMinutes() + time.getMinutes());
            newHours = (current.getHours() + time.getHours());
        }
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), new Time(newMins, newHours));
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        editedPerson.addActivity(activity);
        editedPerson.addPlace(place);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddEventCommand)) {
            return false;
        }

        // state check
        AddEventCommand e = (AddEventCommand) other;
        return index == (e.index)
                && activity.equals(e.activity) && place.equals(e.place) && time.equals(e.time);
    }
}
