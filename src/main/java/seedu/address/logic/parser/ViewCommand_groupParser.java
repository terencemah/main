package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewCommand_group;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object.
 */
public class ViewCommand_groupParser implements Parser<ViewCommand_group> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand.
     *
     * @return A ViewCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public ViewCommand_group parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);
        Index index;
        String parameter;

        if (argMultimap.getValue(PREFIX_GROUP).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand_group.MESSAGE_USAGE));
        }
        index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_GROUP).get());

        if (!argMultimap.getPreamble().isEmpty()) {
            parameter = argMultimap.getPreamble();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand_group.MESSAGE_USAGE));
        }

        if (!parameter.equals(ViewCommand.KEYWORD_PLACE) && !parameter.equals(ViewCommand.KEYWORD_ACTIVITY)) {
            throw new ParseException(String.format(ViewCommand_group.MESSAGE_INVALID_PARAMETER,
                    ViewCommand_group.MESSAGE_USAGE));
        }

        return new ViewCommand_group(index, parameter, ViewCommand_group.TYPE_PERSON);
    }
}
