package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
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
    public static final String MESSAGE_SUCCESS = "New event successfully added: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "Event with given arguments already exists. Please try again."
    public static final String MESSAGE_ARGUMENTS = "Activity: %1$s, Index: %2$d, Place: %3$s, Time: %4$s";

    private final Event toAdd;

    public AddEventCommand(Event event) {
        requireAllNonNull(event);
        this.toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
