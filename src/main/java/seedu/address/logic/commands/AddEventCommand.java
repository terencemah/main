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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an event with a group(g/) or an individual(m/)"
            + "that adds a [activity], [place] and [time] to a group or a person. \n"
            + "[time] has to be at least 2 digits. For example: \n"
            + "5 minutes is 05 \n"
            + "1 Hour and 30 Minutes is 130 \n"
            + "10 Hours and 1 Minute is 1001 \n"
            + "Example usage: "
            + COMMAND_WORD
            + " Dancing "
            + PREFIX_MEMBER
            + "1 "
            + PREFIX_PLACE
            + "SCAPE "
            + PREFIX_TIME
            + "300 \n"
            + COMMAND_WORD
            + " Dancing "
            + PREFIX_GROUP
            + "1 "
            + PREFIX_PLACE
            + "SCAPE "
            + PREFIX_TIME
            + "300";

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
