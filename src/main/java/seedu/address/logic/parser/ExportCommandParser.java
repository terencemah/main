package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIFE;

import java.io.IOException;
import java.util.stream.Stream;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns a ExportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LIFE, PREFIX_GROUP);

        if ((!arePrefixesPresent(argMultimap, PREFIX_LIFE) && !arePrefixesPresent(argMultimap, PREFIX_GROUP))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }
        try {
            String lifePath = "";
            String groupPath = "";

            if (argMultimap.getValue(PREFIX_LIFE).isPresent()) {
                lifePath = ParserUtil.parseExportPath(argMultimap.getValue(PREFIX_LIFE).get());
            }
            if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
                groupPath = ParserUtil.parseExportPath(argMultimap.getValue(PREFIX_GROUP).get());
            }
            return new ExportCommand(lifePath, groupPath);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(ParserUtil.MESSAGE_FILE_ALREADY_EXIST, ExportCommand.MESSAGE_USAGE), pe);
        } catch (IOException io) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE), io);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
     * ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
