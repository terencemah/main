package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Suggests a person/activity/place.
 */
public class SuggestCommand extends Command {
    public static final String COMMAND_WORD = "suggest";

    public static final String KEYWORD_PERSON = "person";
    public static final String KEYWORD_PLACE = "place";
    public static final String KEYWORD_ACTIVITY = "activity";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + ": Suggests a person based on time spent/ "
                    + "place or activity based on frequency"
                    + "Parameters: "
                    + KEYWORD_PERSON
                    + "/"
                    + KEYWORD_ACTIVITY
                    + "/"
                    + KEYWORD_PLACE
                    + "Example: "
                    + COMMAND_WORD
                    + "person";

    public static final String MESSAGE_SUCCESS = "Suggestion provided.";
    public static final String MESSAGE_INVALID_PARAMETER = "The entered parameter is invalid.\n";


    private final String suggestParameter;

    public SuggestCommand(String suggestParameter) {
        requireNonNull(suggestParameter);
        this.suggestParameter = suggestParameter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (suggestParameter.equals("person")) {
            model.suggestPerson();
        } else {
            throw new CommandException(MESSAGE_INVALID_PARAMETER);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, suggestParameter));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SuggestCommand // instanceof handles nulls
                && suggestParameter.equals(((SuggestCommand) other).suggestParameter));
    }

}
