package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Time;

/**
 * Instantiates a new TimeCommand
 */
public class TimeCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Time: %d";

    public static final String COMMAND_WORD = "time";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the time spent with the person identified"
            + "by the index number used in the last person listing. "
            + "Input will be added to the existing time.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "time/ [TIME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "time/ 130";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Time command not implemented yet";

    public static final String MESSAGE_TIME_SPENT_ADDED_SUCCESS = "Time spent with Person increased by: %d";


    private final Index index;
    private final Time time;

    /**
     * @param index of the person in the filtered person list to add the time spend
     * @param time spent with the person to be added to existing time
     */
    public TimeCommand(Index index, Time time) {
        requireAllNonNull(index, time);

        this.index = index;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), new Time(personToEdit.getTimeSpent().getTimeSpent()
                + time.getTimeSpent()));

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Person personToEdit) {
        String message = MESSAGE_TIME_SPENT_ADDED_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimeCommand)) {
            return false;
        }

        TimeCommand e = (TimeCommand) other;
        return index.equals(e.index)
                && (time == (e.time));
    }
}
