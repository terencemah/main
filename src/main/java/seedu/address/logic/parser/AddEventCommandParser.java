package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.person.Time;

import java.util.logging.Logger;

/**
 * Parses input arguments and creates a new {@code AddEventCommand object}
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    private static final Logger logger = LogsCenter.getLogger(AddEventCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddEventCommand}
     * and returns a {@code AddEventCommand} object for execution
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMBER, PREFIX_GROUP, PREFIX_TIME,
                PREFIX_PLACE);

        String activity = argMultimap.getPreamble();
        if (activity.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        String place = argMultimap.getValue(PREFIX_PLACE).get();
        if (place.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        String input = argMultimap.getValue(PREFIX_TIME).get();
        String mins = "";
        String hours = "";
        if (input.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        } else {
            char[] time = argMultimap.getValue(PREFIX_TIME).get().toCharArray();
            if (time.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddEventCommand.MESSAGE_INVALID_TIME_INPUT));
            } else {
                int marker = time.length - 2;
                for (int i = marker; i < time.length; i++) {
                    mins += time[i];
                }
                for (int i = 0; i < marker; i++) {
                    hours += time[i];
                }
                if (hours.equals("")) {
                    hours += "0";
                }
            }
        }

        try {
            Time time = new Time(Integer.parseInt(mins), Integer.parseInt(hours));
            Event event = new Event(activity, place, time);

            Index index;
            if (argMultimap.getValue(PREFIX_MEMBER).isEmpty() && argMultimap.getValue(PREFIX_GROUP).isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
            } else if (argMultimap.getValue(PREFIX_MEMBER).isEmpty()) {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_GROUP).get());
                int idx = index.getOneBased();
                event.setWithGroup(idx);
            } else {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER).get());
                int idx = index.getOneBased();
                event.setWithPerson(idx);
                logger.info("The parsed index is " + idx);
            }

            return new AddEventCommand(event);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

    }
}

