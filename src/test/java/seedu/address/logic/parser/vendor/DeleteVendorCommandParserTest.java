package seedu.address.logic.parser.vendor;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.vendor.DeleteVendorCommand;
import seedu.address.model.vendor.VendorId;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;

public class DeleteVendorCommandParserTest {

    private DeleteVendorCommandParser parser = new DeleteVendorCommandParser();

    @Test
    public void parse_validVendorIdArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "deletevendor vid/123", new DeleteVendorCommand(VENDOR_ID_FIRST_PERSON));
    }

    @Test
    public void parse_invalidVendorIdArgs_throwsParseException() {
        assertParseFailure(parser, "deletevendor vid/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVendorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDeleteCommand_throwsParseException() {
        assertParseFailure(parser, "deletevendor",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVendorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleUniqueIdentifiers_throwsParseException() {
        assertParseFailure(parser, "deletevendor vid/456 vid/123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVendorCommand.MESSAGE_USAGE));
    }

}
