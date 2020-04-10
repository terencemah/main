package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewCommand_group;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParserTest {

    private ViewCommandParser vcp = new ViewCommandParser();

    @Test
    public void parse_nullArgument_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> vcp.parse(null));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> vcp.parse("a"));
    }

    @Test
    public void parse_invalidParameter_throwsParseException() {
        assertThrows(ParseException.class, () -> vcp.parse("1"));
        assertThrows(ParseException.class, () -> vcp.parse("1 t"));
    }

    @Test
    public void parse_emptyIndex_failure() {
        String userInput = " places";
        assertParseFailure(vcp, userInput, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyParameter_failure() {
        String userInput = "1";
        assertParseFailure(vcp, userInput, ViewCommand.MESSAGE_INVALID_PARAMETER + " " +
                ViewCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidParameter_failure() {
        String userInput = "1 test";
        assertParseFailure(vcp, userInput, ViewCommand.MESSAGE_INVALID_PARAMETER
                + ViewCommand.MESSAGE_USAGE);
    }
}
