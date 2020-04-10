package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand_group;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommand_groupParserTest {

    private ViewCommand_groupParser parser = new ViewCommand_groupParser();

    @Test
    public void parse_emptyIndex_failure() {
        String userInput = "places "
                + PREFIX_GROUP;
        assertParseFailure(parser, userInput, "Index is not a non-zero unsigned integer.");
    }

    @Test
    public void parse_emptyParameter_failure() {
        String userInput = " " + PREFIX_GROUP + "1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand_group.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidParameter_failure() {
        String userInput = "test" + PREFIX_GROUP + "1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand_group.MESSAGE_USAGE));
    }

    @Test
    public void parse_allValidArguments_success() throws ParseException {
        String parameter = "activities";
        String idx = "1";
        String userInput = parameter + " " + PREFIX_GROUP + idx;
        Index index = ParserUtil.parseIndex(idx);
        ViewCommand_group expectedCommand = new ViewCommand_group(index, parameter, ViewCommand_group.TYPE_PERSON);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
