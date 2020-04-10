package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewCommand_Group;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object.
 */
public class ViewCommand_GroupParser implements Parser<ViewCommand_Group> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand.
     *
     * @return A ViewCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public ViewCommand_Group parse(String args) throws ParseException {
        requireNonNull(args);
        StringTokenizer st = new StringTokenizer(args);
        Index index;
        String parameter;
        String token = "";

        try {
            token = st.nextToken();
            index = ParserUtil.parseIndex(token);
        } catch (NoSuchElementException | ParseException pe) {
            if (token.equals(ViewCommand.KEYWORD_TIME)) {
                index = ParserUtil.parseIndex("1");
                parameter = ViewCommand.KEYWORD_TIME;
                return new ViewCommand_Group(index, parameter, ViewCommand.TYPE_ALL);
            } else if (token.equals(ViewCommand.KEYWORD_ALL)) {
                index = ParserUtil.parseIndex("1");
                parameter = ViewCommand.KEYWORD_ALL;
                return new ViewCommand_Group(index, parameter, ViewCommand.TYPE_ALL);
            } else if (token.equals(ViewCommand.KEYWORD_RECENT)) {
                index = ParserUtil.parseIndex("1");
                parameter = ViewCommand.KEYWORD_RECENT;
                return new ViewCommand_Group(index, parameter, ViewCommand.TYPE_ALL);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), pe);
            }
        }

        if (st.hasMoreTokens()) {
            parameter = st.nextToken();
        } else {
            throw new ParseException(ViewCommand.MESSAGE_INVALID_PARAMETER + " " + ViewCommand.MESSAGE_USAGE);
        }

        if (!parameter.equals(ViewCommand.KEYWORD_PLACE) && !parameter.equals(ViewCommand.KEYWORD_ACTIVITY)
                && !parameter.equals(ViewCommand.KEYWORD_RECENT) && !parameter.equals(ViewCommand.KEYWORD_ALL)) {
            throw new ParseException(ViewCommand.MESSAGE_INVALID_PARAMETER + ViewCommand.MESSAGE_USAGE);
        }

        return new ViewCommand_Group(index, parameter, ViewCommand_Group.TYPE_PERSON);
    }
}
