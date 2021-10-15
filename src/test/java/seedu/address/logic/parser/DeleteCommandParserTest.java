package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validPassportNumberArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "delete pn/E0123122G", new DeleteCommand(PASSPORT_NUMBER_FIRST_PERSON));
    }

    @Test
    public void parse_validStaffIdArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "delete sid/123", new DeleteCommand(STAFF_ID_FIRST_PERSON));
    }

    @Test
    public void parse_invalidPassportNumberArgs_throwsParseException() {
        assertParseFailure(parser, "delete pn/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStaffIdArgs_throwsParseException() {
        assertParseFailure(parser, "delete sid/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
