package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_CARL;
import static seedu.address.logic.commands.CommandTestUtil.STAFF_ID_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.STAFF_ID_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.IdentifierContainsKeywordsPredicate;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_MISSING_ARGUMENTS, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_twoSid_throwsParseException() {
        assertParseFailure(parser, ViewCommand.COMMAND_WORD + " " + STAFF_ID_DESC_DANIEL + " " + STAFF_ID_DESC_ELLE,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_twoPassport_throwsParseException() {
        assertParseFailure(parser,
                ViewCommand.COMMAND_WORD + " " + PASSPORT_NUMBER_DESC_ALICE + " " + PASSPORT_NUMBER_DESC_CARL,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_oneSidOnePassport_throwsParseException() {
        assertParseFailure(parser,
                ViewCommand.COMMAND_WORD + " " + STAFF_ID_DESC_DANIEL + " " + PASSPORT_NUMBER_DESC_BENSON,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        ViewCommand expectedViewCommand =
                new ViewCommand(new IdentifierContainsKeywordsPredicate(List.of(VALID_STAFF_ID_DANIEL)));
        assertParseSuccess(parser, STAFF_ID_DESC_DANIEL, expectedViewCommand);

        // whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_STAFF_ID + "     " + VALID_STAFF_ID_DANIEL, expectedViewCommand);
    }

}
