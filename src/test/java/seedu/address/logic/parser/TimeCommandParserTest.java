package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TimeCommand;
import seedu.address.model.person.Time;

public class TimeCommandParserTest {
    private TimeCommandParser parser = new TimeCommandParser();
    private final int initTime = 0;

    @Test
    public void parse_indexSpecified_success() {
        // have time
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_TIME + initTime;
        TimeCommand expectedCommand = new TimeCommand(INDEX_FIRST_PERSON, new Time(initTime));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = targetIndex.getOneBased() + " " + PREFIX_TIME;
        expectedCommand = new TimeCommand(INDEX_FIRST_PERSON, new Time(0));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimeCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, TimeCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, TimeCommand.COMMAND_WORD + " " + initTime, expectedMessage);
    }
}