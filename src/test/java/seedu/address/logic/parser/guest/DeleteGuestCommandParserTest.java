package seedu.address.logic.parser.guest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.guest.DeleteGuestCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteGuestCommandParserTest {

    private DeleteGuestCommandParser parser = new DeleteGuestCommandParser();

    @Test
    public void parse_validPassportNumberArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "deleteguest pn/12332131D", new DeleteGuestCommand(PASSPORT_NUMBER_FIRST_PERSON));
    }

    @Test
    public void parse_invalidPassportNumberArgs_throwsParseException() {
        assertParseFailure(parser, "deleteguest pn/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGuestCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDeleteCommand_throwsParseException() {
        assertParseFailure(parser, "deleteguest",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGuestCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleUniqueIdentifiers_throwsParseException() {
        assertParseFailure(parser, "deleteguest pn/456212312D pn/123312221E",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGuestCommand.MESSAGE_USAGE));
    }
}
