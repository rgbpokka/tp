package seedu.address.logic.parser.guest;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_CARL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class FilterGuestCommandParserTest {

    private FilterGuestCommandParser parser = new FilterGuestCommandParser();

//    @Test
//    public void parse_emptyArg_throwsParseException() {
//        assertParseFailure(parser, "     ", String.format(MESSAGE_MISSING_ARGUMENTS, FilterCommand.MESSAGE_USAGE));
//    }
//
//    @Test
//    public void parse_wrongArguments_throwsParseException() {
//        assertParseFailure(parser, FilterCommand.COMMAND_WORD + " " + STAFF_ID_DESC_DANIEL + " " + STAFF_ID_DESC_ELLE,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
//    }
//
//    @Test
//    public void parse_moreWrongArguments_throwsParseException() {
//        assertParseFailure(parser,
//                FilterCommand.COMMAND_WORD + " " + PASSPORT_NUMBER_DESC_ALICE + " " + PASSPORT_NUMBER_DESC_CARL,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
//    }
//
//    @Test
//    public void parse_prefixButNoArg_throwsParseException() {
//        assertParseFailure(parser,
//                " " + PREFIX_TAG,
//                String.format(MESSAGE_MISSING_ARGUMENTS, FilterCommand.MESSAGE_USAGE));
//    }
//
//    @Test
//    public void parse_validArgs_returnsFilterCommand() {
//        // no leading and trailing whitespaces
//        FilterCommand expectedFilterCommand =
//                new FilterCommand(new TagContainsKeywordsPredicate(List.of(new Tag(VALID_TAG_ALICE))));
//        assertParseSuccess(parser, TAG_DESC_ALICE, expectedFilterCommand);
//
//        // whitespaces between keywords
//        assertParseSuccess(parser, " " + PREFIX_TAG + "     " + VALID_TAG_ALICE, expectedFilterCommand);
//    }
}
