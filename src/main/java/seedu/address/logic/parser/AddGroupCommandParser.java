package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Stream;

/**
 * Parses the input argument to create a new AddGroupCommand instance
 */
public class AddGroupCommandParser implements Parser<AddGroupCommand> {

    public AddGroupCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MEMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Group group = new Group(argMultimap.getValue(PREFIX_NAME).get());
        if (arePrefixesPresent(argMultimap, PREFIX_MEMBER)) {
            List<String> members = argMultimap.getAllValues(PREFIX_MEMBER);
            for(int i = 0; i < members.size(); i++) {
                group.addPerson(Integer.parseInt(members.get(i)));
            }
        }
        return new AddGroupCommand(group);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
