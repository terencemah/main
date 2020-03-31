package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ViewCommandParserTest {

    ViewCommandParser vcp = new ViewCommandParser();

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
        assertThrows(ParseException.class, () -> vcp. parse("1 t"));
    }
}
