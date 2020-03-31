package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {
    private static final String TEST_LIFE_CSV = Paths.get("src", "test",
            "data", "CsvFilesTest", "life.csv").toString();
    private static final String TEST_GROUP_CSV = Paths.get("src", "test",
            "data", "CsvFilesTest", "group.csv").toString();
    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(
                parser, "some invalid string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsImportCommand() {
        // no leading and trailing whitespaces
        ImportCommand expectedImportCommand =
                new ImportCommand(TEST_LIFE_CSV, "");
        assertParseSuccess(parser, " l/" + TEST_LIFE_CSV + " g/" + TEST_GROUP_CSV, expectedImportCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n l/" + TEST_LIFE_CSV
                + " \n \t g/" + TEST_GROUP_CSV + "\t", expectedImportCommand);
    }

}
