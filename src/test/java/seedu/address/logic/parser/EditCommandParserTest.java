package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_UNIQUE_IDENTIFIER;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSPORT_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROOM_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.PASSPORT_NUMBER_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.ROOM_NUMBER_DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.STAFF_ID_DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_DELUXE_ROOM;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SENIOR_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_VIP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DELUXE_ROOM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SENIOR_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_VIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_FIRST_PERSON;


import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.address.logic.commands.EditCommand.EditStaffDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PassportNumber;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.StaffId;
import seedu.address.model.person.UniqueIdentifier;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditGuestDescriptorBuilder;
import seedu.address.testutil.EditStaffDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_validPassportNumberArgs_returnsEditCommand() {
        EditGuestDescriptor editGuestDescriptor = new EditGuestDescriptor();
        editGuestDescriptor.setPassportNumber(new PassportNumber("E0123122G"));
        assertParseSuccess(parser,
                EditCommand.COMMAND_WORD
                + " "
                + PREFIX_PASSPORT_NUMBER
                + PASSPORT_NUMBER_FIRST_PERSON,
                new EditCommand(PASSPORT_NUMBER_FIRST_PERSON, editGuestDescriptor));
    }

    @Test
    public void parse_validStaffIdArgs_returnsEditCommand() {
        // why did this make a difference?
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptor();
        editStaffDescriptor.setStaffId(new StaffId("123"));
        assertParseSuccess(parser,
                EditCommand.COMMAND_WORD
                + " "
                + PREFIX_STAFF_ID
                + STAFF_ID_FIRST_PERSON,
                new EditCommand(STAFF_ID_FIRST_PERSON, editStaffDescriptor));
    }

    @Test
    public void parse_invalidValueForStaff_failure() {
        String partialUserInput = EditCommand.COMMAND_WORD
                + " "
                + PREFIX_STAFF_ID
                + STAFF_ID_FIRST_PERSON;

        assertParseFailure(parser, "edit sid/", StaffId.MESSAGE_CONSTRAINTS); // invalid staff id
        assertParseFailure(parser, partialUserInput + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, partialUserInput + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, partialUserInput + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, partialUserInput + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, partialUserInput + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, partialUserInput + INVALID_PHONE_DESC + EMAIL_DESC_ALICE, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, partialUserInput + PHONE_DESC_DANIEL + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, partialUserInput + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, partialUserInput + TAG_DESC_SENIOR_STAFF + TAG_EMPTY + TAG_DESC_CHEF, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, partialUserInput + TAG_EMPTY + TAG_DESC_SENIOR_STAFF + TAG_DESC_CHEF, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, partialUserInput + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_DANIEL + VALID_PHONE_DANIEL,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValueForGuest_failure() {
        String partialUserInput = EditCommand.COMMAND_WORD
                + " "
                + PREFIX_PASSPORT_NUMBER
                + PASSPORT_NUMBER_FIRST_PERSON;

        assertParseFailure(parser, "edit pn/", PassportNumber.MESSAGE_CONSTRAINTS); // invalid passport number
        assertParseFailure(parser, partialUserInput + INVALID_PASSPORT_NUMBER_DESC, PassportNumber.MESSAGE_CONSTRAINTS); // invalid room number
        assertParseFailure(parser, partialUserInput + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, partialUserInput + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, partialUserInput + INVALID_ROOM_NUMBER_DESC, RoomNumber.MESSAGE_CONSTRAINTS); // invalid room number
        assertParseFailure(parser, partialUserInput + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid room number followed by valid email
        assertParseFailure(parser, partialUserInput + INVALID_ROOM_NUMBER_DESC + EMAIL_DESC_ALICE, RoomNumber.MESSAGE_CONSTRAINTS);

        // valid room number followed by invalid room number. The test case for invalid room number followed by valid room number
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, partialUserInput + VALID_ROOM_NUMBER_ALICE + INVALID_ROOM_NUMBER_DESC, RoomNumber.MESSAGE_CONSTRAINTS);


        // is this still relevant for us?
//        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
//        // parsing it together with a valid tag results in error
//        assertParseFailure(parser, partialUserInput + VALID_TAG_VIP + VALID_TAG_DELUXE_ROOM + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
//        assertParseFailure(parser, partialUserInput + VALID_TAG_VIP + TAG_EMPTY + VALID_TAG_DELUXE_ROOM, Tag.MESSAGE_CONSTRAINTS);
//        assertParseFailure(parser, partialUserInput + TAG_EMPTY + VALID_TAG_VIP + VALID_TAG_DELUXE_ROOM, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, partialUserInput + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ROOM_NUMBER_ALICE + VALID_PASSPORT_NUMBER_ALICE,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecifiedForStaff_success() {
        UniqueIdentifier targetIdentifier = new StaffId(VALID_STAFF_ID_DANIEL);
        String userInput = EditCommand.COMMAND_WORD
                + STAFF_ID_DESC_DANIEL
                + PHONE_DESC_DANIEL
                + TAG_DESC_CHEF
                + EMAIL_DESC_DANIEL
                + ADDRESS_DESC_DANIEL
                + NAME_DESC_DANIEL
                + TAG_DESC_SENIOR_STAFF;

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder()
                .withStaffId(VALID_STAFF_ID_DANIEL)
                .withName(VALID_NAME_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .withTags(VALID_TAG_CHEF, VALID_TAG_SENIOR_STAFF).build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecifiedForGuest_success() {
        UniqueIdentifier targetIdentifier = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditCommand.COMMAND_WORD
                + PASSPORT_NUMBER_DESC_ALICE
                + TAG_DESC_DELUXE_ROOM
                + NAME_DESC_ALICE
                + EMAIL_DESC_ALICE
                + TAG_DESC_VIP
                + ROOM_NUMBER_DESC_ALICE;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withName(VALID_NAME_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withTags(VALID_TAG_VIP, VALID_TAG_DELUXE_ROOM)
                .build();

        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecifiedForStaff_success() {
        UniqueIdentifier targetIdentifier = new StaffId(VALID_STAFF_ID_DANIEL);
        String userInput = EditCommand.COMMAND_WORD
                + STAFF_ID_DESC_DANIEL
                + ADDRESS_DESC_DANIEL;

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder()
                .withStaffId(VALID_STAFF_ID_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecifiedForGuest_success() {
        UniqueIdentifier targetIdentifier = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditCommand.COMMAND_WORD
                + PASSPORT_NUMBER_DESC_ALICE
                + ROOM_NUMBER_DESC_ALICE;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .build();

        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFieldsForStaff_acceptsLast() {
        UniqueIdentifier targetIdentifier = new StaffId(VALID_STAFF_ID_DANIEL);
        String userInput = EditCommand.COMMAND_WORD
                + STAFF_ID_DESC_DANIEL + PHONE_DESC_DANIEL + ADDRESS_DESC_DANIEL + EMAIL_DESC_DANIEL
                + TAG_DESC_SENIOR_STAFF + PHONE_DESC_ELLE + ADDRESS_DESC_ELLE + EMAIL_DESC_ELLE + TAG_DESC_SENIOR_STAFF
                + PHONE_DESC_DANIEL + ADDRESS_DESC_DANIEL + EMAIL_DESC_DANIEL + TAG_DESC_CHEF;

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withStaffId(VALID_STAFF_ID_DANIEL)
                .withPhone(VALID_PHONE_DANIEL).withEmail(VALID_EMAIL_DANIEL).withAddress(VALID_ADDRESS_DANIEL)
                .withTags(VALID_TAG_SENIOR_STAFF, VALID_TAG_CHEF).build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFieldsForGuest_acceptsLast() {
        UniqueIdentifier targetIdentifier = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditCommand.COMMAND_WORD
                + PASSPORT_NUMBER_DESC_BENSON
                + NAME_DESC_BENSON
                + ROOM_NUMBER_DESC_BENSON
                + PASSPORT_NUMBER_DESC_ALICE
                + TAG_DESC_DELUXE_ROOM
                + NAME_DESC_ALICE
                + EMAIL_DESC_ALICE
                + TAG_DESC_VIP
                + ROOM_NUMBER_DESC_ALICE;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withName(VALID_NAME_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withTags(VALID_TAG_VIP, VALID_TAG_DELUXE_ROOM)
                .build();

        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValueForStaff_success() {
        // no other valid values specified
        UniqueIdentifier targetIdentifier = new StaffId(VALID_STAFF_ID_DANIEL);
        String userInput = EditCommand.COMMAND_WORD + STAFF_ID_DESC_DANIEL + INVALID_PHONE_DESC + PHONE_DESC_DANIEL;
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder()
                .withStaffId(VALID_STAFF_ID_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EditCommand.COMMAND_WORD + STAFF_ID_DESC_DANIEL + EMAIL_DESC_DANIEL + INVALID_PHONE_DESC
                + ADDRESS_DESC_DANIEL + PHONE_DESC_DANIEL;
        descriptor = new EditStaffDescriptorBuilder()
                .withStaffId(VALID_STAFF_ID_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .build();
        expectedCommand = new EditCommand(targetIdentifier, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValueForGuest_success() {
        UniqueIdentifier targetIdentifier = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditCommand.COMMAND_WORD + PASSPORT_NUMBER_DESC_ALICE + INVALID_EMAIL_DESC + ROOM_NUMBER_DESC_ALICE + EMAIL_DESC_ALICE;
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTagsForStaff_success() {
        UniqueIdentifier targetIdentifier = new StaffId(VALID_STAFF_ID_DANIEL);
        String userInput = EditCommand.COMMAND_WORD + STAFF_ID_DESC_DANIEL + TAG_EMPTY;

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withStaffId(VALID_STAFF_ID_DANIEL).withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTagsForGuest_success() {
        UniqueIdentifier targetIdentifier = new PassportNumber(VALID_PASSPORT_NUMBER_ALICE);
        String userInput = EditCommand.COMMAND_WORD + PASSPORT_NUMBER_DESC_ALICE + TAG_EMPTY;

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder().withPassportNumber(VALID_PASSPORT_NUMBER_ALICE).withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIdentifier, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
