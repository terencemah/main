package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.util.Time;

/**
 * Represents the command to add a new event to CoderLifeInsights.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "add_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an event with a group(g/) or an individual(m/)"
            + "that adds a [activity], [place] and [time] to a group or a person";

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
        personToEdit.getTime().setMinutes(newMins);
        personToEdit.getTime().setHours(newHours);
        personToEdit.addActivity(activity);
        personToEdit.addPlace(place);

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
