package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STAFF_ID;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;

import static seedu.address.logic.commands.CommandTestUtil.STAFF_ID_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SENIOR_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SENIOR_STAFF;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.DANIEL_STAFF;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StaffBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        Person expectedPerson = new StaffBuilder(DANIEL_STAFF).withTags(VALID_TAG_SENIOR_STAFF).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                + ADDRESS_DESC_DANIEL + TAG_DESC_CHEF, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ELLE + NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                + ADDRESS_DESC_DANIEL + TAG_DESC_CHEF, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_ELLE + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL
                + ADDRESS_DESC_DANIEL + TAG_DESC_CHEF, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_ELLE + EMAIL_DESC_DANIEL
                + ADDRESS_DESC_DANIEL + TAG_DESC_CHEF, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted

        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL + ADDRESS_DESC_ELLE
                + ADDRESS_DESC_DANIEL + TAG_DESC_CHEF, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new StaffBuilder(DANIEL_STAFF).withTags(VALID_TAG_CHEF, VALID_TAG_SENIOR_STAFF)
                .build();

        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL + ADDRESS_DESC_DANIEL
                + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF, new AddCommand(expectedPersonMultipleTags));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags

        Person expectedPerson = new StaffBuilder(DANIEL_STAFF).withTags().build();
        assertParseSuccess(parser, NAME_DESC_DANIEL + PHONE_DESC_DANIEL + EMAIL_DESC_DANIEL + ADDRESS_DESC_DANIEL,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + STAFF_ID_DESC_ELLE, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_ELLE + VALID_PHONE_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                        + STAFF_ID_DESC_ELLE, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + VALID_EMAIL_ELLE + ADDRESS_DESC_ELLE
                        + STAFF_ID_DESC_ELLE, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + VALID_ADDRESS_ELLE
                        + STAFF_ID_DESC_ELLE, expectedMessage);

        // missing staff id prefix
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + VALID_ADDRESS_ELLE
                + STAFF_ID_DESC_ELLE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_ELLE + VALID_PHONE_ELLE + VALID_EMAIL_ELLE + VALID_ADDRESS_ELLE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF + STAFF_ID_DESC_ELLE, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_ELLE + INVALID_PHONE_DESC + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF + STAFF_ID_DESC_ELLE, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + INVALID_EMAIL_DESC + ADDRESS_DESC_ELLE
                + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF + STAFF_ID_DESC_ELLE, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + INVALID_ADDRESS_DESC
                + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF + STAFF_ID_DESC_ELLE, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + INVALID_TAG_DESC + VALID_TAG_CHEF + STAFF_ID_DESC_ELLE, Tag.MESSAGE_CONSTRAINTS);

        // invalid staff id
        assertParseFailure(parser, NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + ADDRESS_DESC_ELLE
                + TAG_DESC_SENIOR_STAFF + VALID_TAG_CHEF + INVALID_STAFF_ID, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_ELLE + EMAIL_DESC_ELLE + INVALID_ADDRESS_DESC
                        + STAFF_ID_DESC_ELLE, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_ELLE + PHONE_DESC_ELLE + EMAIL_DESC_ELLE
                + ADDRESS_DESC_ELLE + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF + STAFF_ID_DESC_ELLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
