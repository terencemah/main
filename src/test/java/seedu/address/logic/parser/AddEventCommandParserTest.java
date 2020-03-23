package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.model.person.Time;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();
    private final String nonEmptyEvent = "Some event.";

    @Test
    public void parse_memberIndexSpecified_success() {
        String targetIndex = "1";
        String activity = "Some activity";
        String place = "Anywhere";
        String time = "1111";
        String userInput = activity + " "
                + PREFIX_MEMBER + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        AddEventCommand expectedCommand = new AddEventCommand(activity, Integer.parseInt(targetIndex), place,
                new Time(11, 11));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_groupIndexSpecified_success() {
        String targetIndex = "1";
        String activity = "Some activity";
        String place = "Anywhere";
        String time = "1111";
        String userInput = activity + " "
                + PREFIX_GROUP + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        AddEventCommand expectedCommand = new AddEventCommand(activity, Integer.parseInt(targetIndex), place,
                new Time(11, 11));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidTimeField_failure() {
        String targetIndex = "1";
        String activity = "Some activity";
        String place = "Anywhere";
        String time = "1";
        String userInput = activity + " "
                + PREFIX_GROUP + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_INVALID_TIME_INPUT));
    }

    @Test
    public void parse_emptyHourField_success () {
        String targetIndex = "1";
        String activity = "Some activity";
        String place = "Anywhere";
        String time = "11";
        String userInput = activity + " "
                + PREFIX_GROUP + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        AddEventCommand expectedCommand = new AddEventCommand(activity, Integer.parseInt(targetIndex), place,
                new Time(11, 0));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyActivity_failure() {
        String targetIndex = "1";
        String activity = "";
        String place = "";
        String time = "15";
        String userInput = activity + " "
                + PREFIX_GROUP + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyTime_failure() {
        String targetIndex = "1";
        String activity = "Some activity";
        String place = "Some place";
        String time = "1";
        String userInput = activity + " "
                + PREFIX_GROUP + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_INVALID_TIME_INPUT));
    }

    @Test
    public void parse_emptyPlace_failure() {
        String targetIndex = "1";
        String activity = "Some activity";
        String place = "";
        String time = "15";
        String userInput = activity + " "
                + PREFIX_GROUP + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyIndex_failure() {
        String targetIndex = "";
        String activity = "Some activity";
        String place = "Some place";
        String time = "5";
        String userInput = activity + " "
                + PREFIX_GROUP + targetIndex + " "
                + PREFIX_PLACE + place + " "
                + PREFIX_TIME + time;
        assertParseFailure(parser, userInput, "Index is not a non-zero unsigned integer.");
    }
}
