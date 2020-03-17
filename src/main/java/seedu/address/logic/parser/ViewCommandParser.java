package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.StringTokenizer;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParser implements Parser<ViewCommand> {

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
                && !parameter.equals(ViewCommand.KEYWORD_TIME)) {
            throw new ParseException(ViewCommand.MESSAGE_INVALID_PARAMETER + ViewCommand.MESSAGE_USAGE);
        }

        return new ViewCommand(index, parameter);
    }
}
