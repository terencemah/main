package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.StringTokenizer;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand.
     * @return A ViewCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        StringTokenizer st = new StringTokenizer(args);
        Index index;
        String parameter;

        try {
            index = ParserUtil.parseIndex(st.nextToken());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
        }

        if (st.hasMoreTokens()) {
            parameter = st.nextToken();
        } else {
            throw new ParseException(ViewCommand.MESSAGE_INVALID_PARAMETER + " " + ViewCommand.MESSAGE_USAGE);
        }

        if (!parameter.equals(ViewCommand.KEYWORD_PLACE) && !parameter.equals(ViewCommand.KEYWORD_ACTIVITY)
                && !parameter.equals(ViewCommand.KEYWORD_RECENT)) {
            throw new ParseException(ViewCommand.MESSAGE_INVALID_PARAMETER + ViewCommand.MESSAGE_USAGE);
        }

        return new ViewCommand(index, parameter);
    }
}
