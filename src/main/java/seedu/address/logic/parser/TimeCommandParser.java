package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Time;

/**
 * Parses input arguments and creates a new {@code TimeCommand} object
 */
public class TimeCommandParser implements Parser<TimeCommand> {

    /**
     * Parses input arguments and creates a new {@code TimeCommand} object
     */
    public TimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TIME);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimeCommand.MESSAGE_USAGE), ive);
        }

        String time = argMultimap.getValue(PREFIX_TIME).orElse("");

        return new TimeCommand(index, new Time(Integer.parseInt(time)));
    }
}
