package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.EventDescriptor;
import seedu.address.model.person.Person;

/**
 * Shows the user the places visited, activities done or last 5 events
 * with a chosen person from the Address Book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String KEYWORD_PLACE = "places";
    public static final String KEYWORD_ACTIVITY = "activities";
    public static final String KEYWORD_RECENT = "recent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays insights about the places visited, "
            + "activities done, or time spent with the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) [INSIGHT_PARAMETER]\n"
            + "[INSIGHT_PARAMETER] can be [" + KEYWORD_PLACE + "], [" + KEYWORD_ACTIVITY
            + "], or [" + KEYWORD_RECENT + ".\n"
            + "Example: " + COMMAND_WORD + " 1 " + KEYWORD_PLACE;

    public static final String MESSAGE_PLACE = "Displaying places visited with ";
    public static final String MESSAGE_ACTIVITY = "Displaying activities done with ";
    public static final String MESSAGE_RECENT = "Listing last 5 events with ";
    public static final String MESSAGE_INVALID_PARAMETER = "The entered parameter is invalid.\n";

    private final Index index;
    private final String parameter;

    public ViewCommand(Index index, String parameter) {
        requireNonNull(index);
        requireNonNull(parameter);

        this.index = index;
        this.parameter = parameter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(index.getZeroBased());
        ViewType vt;
        String message;

        switch (parameter) {

        case KEYWORD_PLACE:
            model.showPlaceList(personToView);
            message = MESSAGE_PLACE + personToView.getName() + ".";
            vt = ViewType.PLACES;
            break;

        case KEYWORD_ACTIVITY:
            model.showActivityList(personToView);
            message = MESSAGE_ACTIVITY + personToView.getName() + ".";
            vt = ViewType.ACTIVITIES;
            break;

        default:
            model.showRecentList(personToView);
            message = MESSAGE_RECENT + personToView.getName() + ".";
            vt = ViewType.RECENT;
        }


        return new CommandResult(message, vt);
    }

    public Index getIndex() {
        return index;
    }

    public String getParam() {
        return parameter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ViewCommand)) {
            return false;
        }
        return ((ViewCommand) o).getIndex().equals(this.getIndex())
                && ((ViewCommand) o).getParam() == this.getParam();
    }
}
