package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.TypicalPassportNumbers.PASSPORT_NUMBER_THIRD_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_FOURTH_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_THIRD_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Guest;
import seedu.address.model.person.IdentifierContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.testutil.EditGuestDescriptorBuilder;
import seedu.address.testutil.EditStaffDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Guests
    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_EMAIL_ALICE = "alice@example.com";
    public static final String[] VALID_TAG_ALICE = {"VIP"};
    public static final String VALID_ROOM_NUMBER_ALICE = "20202";
    public static final String VALID_PASSPORT_NUMBER_ALICE = PASSPORT_NUMBER_FIRST_PERSON.toString();
    public static final String NAME_DESC_ALICE = " " + PREFIX_NAME + VALID_NAME_ALICE;
    public static final String ROOM_NUMBER_DESC_ALICE = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_ALICE;
    public static final String EMAIL_DESC_ALICE = " " + PREFIX_EMAIL + VALID_EMAIL_ALICE;
    public static final String PASSPORT_NUMBER_DESC_ALICE = " " + PREFIX_PASSPORT_NUMBER + VALID_PASSPORT_NUMBER_ALICE;
    public static final String TAG_DESC_ALICE = " " + PREFIX_TAG + VALID_TAG_ALICE;

    public static final String VALID_NAME_BENSON = "Benson Meier";
    public static final String VALID_EMAIL_BENSON = "benson@example.com";
    public static final String[] VALID_TAG_BENSON = {"NORMALROOM", "OUTSTANDINGPAYMENT"};
    public static final String VALID_ROOM_NUMBER_BENSON = "20201";
    public static final String VALID_PASSPORT_NUMBER_BENSON = PASSPORT_NUMBER_SECOND_PERSON.toString();
    public static final String NAME_DESC_BENSON = " " + PREFIX_NAME + VALID_NAME_BENSON;
    public static final String ROOM_NUMBER_DESC_BENSON = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_BENSON;
    public static final String EMAIL_DESC_BENSON = " " + PREFIX_EMAIL + VALID_EMAIL_BENSON;
    public static final String PASSPORT_NUMBER_DESC_BENSON = " " + PREFIX_PASSPORT_NUMBER
            + VALID_PASSPORT_NUMBER_BENSON;
    public static final String TAG_DESC_BENSON = " " + PREFIX_TAG + VALID_TAG_BENSON;

    public static final String VALID_NAME_CARL = "Carl Kurz";
    public static final String VALID_EMAIL_CARL = "carl@example.com";
    public static final String[] VALID_TAG_CARL = {"DELUXESUITE", "PAID"};
    public static final String VALID_ROOM_NUMBER_CARL = "12321";
    public static final String VALID_PASSPORT_NUMBER_CARL = PASSPORT_NUMBER_THIRD_PERSON.toString();
    public static final String NAME_DESC_CARL = " " + PREFIX_NAME + VALID_NAME_CARL;
    public static final String ROOM_NUMBER_DESC_CARL = " " + PREFIX_ROOM_NUMBER + VALID_ROOM_NUMBER_CARL;
    public static final String EMAIL_DESC_CARL = " " + PREFIX_EMAIL + VALID_EMAIL_CARL;
    public static final String PASSPORT_NUMBER_DESC_CARL = " " + PREFIX_PASSPORT_NUMBER + VALID_PASSPORT_NUMBER_CARL;
    public static final String TAG_DESC_CARL = " " + PREFIX_TAG + VALID_TAG_CARL;

    // Staff
    public static final String VALID_NAME_DANIEL = "Daniel Meier";
    public static final String VALID_EMAIL_DANIEL = "cornelia@example.com";
    public static final String[] VALID_TAG_DANIEL = {"COUNTER STAFF"};
    public static final String VALID_ADDRESS_DANIEL = "10th street";
    public static final String VALID_PHONE_DANIEL = "87652533";
    public static final String VALID_STAFF_ID_DANIEL = STAFF_ID_FIRST_PERSON.toString();
    public static final String NAME_DESC_DANIEL = " " + PREFIX_NAME + VALID_NAME_DANIEL;
    public static final String EMAIL_DESC_DANIEL = " " + PREFIX_EMAIL + VALID_EMAIL_DANIEL;
    public static final String ADDRESS_DESC_DANIEL = " " + PREFIX_ADDRESS + VALID_ADDRESS_DANIEL;
    public static final String PHONE_DESC_DANIEL = " " + PREFIX_PHONE + VALID_PHONE_DANIEL;
    public static final String STAFF_ID_DESC_DANIEL = " " + PREFIX_STAFF_ID + VALID_STAFF_ID_DANIEL;

    public static final String VALID_NAME_ELLE = "Elle Meyer";
    public static final String VALID_EMAIL_ELLE = "elle@example.com";
    public static final String[] VALID_TAG_ELLE = {"MANAGER"};
    public static final String VALID_ADDRESS_ELLE = "michegan ave";
    public static final String VALID_PHONE_ELLE = "9482224";
    public static final String VALID_STAFF_ID_ELLE = STAFF_ID_SECOND_PERSON.toString();
    public static final String NAME_DESC_ELLE = " " + PREFIX_NAME + VALID_NAME_ELLE;
    public static final String EMAIL_DESC_ELLE = " " + PREFIX_EMAIL + VALID_EMAIL_ELLE;
    public static final String ADDRESS_DESC_ELLE = " " + PREFIX_ADDRESS + VALID_ADDRESS_ELLE;
    public static final String PHONE_DESC_ELLE = " " + PREFIX_PHONE + VALID_PHONE_ELLE;
    public static final String STAFF_ID_DESC_ELLE = " " + PREFIX_STAFF_ID + VALID_STAFF_ID_ELLE;

    public static final String VALID_NAME_FIONA = "Fiona Kunz";
    public static final String VALID_EMAIL_FIONA = "fiona@example.com";
    public static final String[] VALID_TAG_FIONA = {"Waitress"};
    public static final String VALID_ADDRESS_FIONA = "little tokyo";
    public static final String VALID_PHONE_FIONA = "9482427";
    public static final String VALID_STAFF_ID_FIONA = STAFF_ID_THIRD_PERSON.toString();
    public static final String NAME_DESC_FIONA = " " + PREFIX_NAME + VALID_NAME_FIONA;
    public static final String EMAIL_DESC_FIONA = " " + PREFIX_EMAIL + VALID_EMAIL_FIONA;
    public static final String ADDRESS_DESC_FIONA = " " + PREFIX_ADDRESS + VALID_ADDRESS_FIONA;
    public static final String PHONE_DESC_FIONA = " " + PREFIX_PHONE + VALID_PHONE_FIONA;
    public static final String STAFF_ID_DESC_FIONA = " " + PREFIX_STAFF_ID + VALID_STAFF_ID_FIONA;

    public static final String VALID_NAME_GEORGE = "George Best";
    public static final String VALID_EMAIL_GEORGE = "george@example.com";
    public static final String[] VALID_TAG_GEORGE = {"Head of Staff"};
    public static final String VALID_ADDRESS_GEORGE = "4th street";
    public static final String VALID_PHONE_GEORGE = "9482442";
    public static final String VALID_STAFF_ID_GEORGE = STAFF_ID_FOURTH_PERSON.toString();
    public static final String NAME_DESC_GEORGE = " " + PREFIX_NAME + VALID_NAME_GEORGE;
    public static final String EMAIL_DESC_GEORGE = " " + PREFIX_EMAIL + VALID_EMAIL_GEORGE;
    public static final String ADDRESS_DESC_GEORGE = " " + PREFIX_ADDRESS + VALID_ADDRESS_GEORGE;
    public static final String PHONE_DESC_GEORGE = " " + PREFIX_PHONE + VALID_PHONE_GEORGE;
    public static final String STAFF_ID_DESC_GEORGE = " " + PREFIX_STAFF_ID + VALID_STAFF_ID_GEORGE;

    // Guest Tags
    public static final String VALID_TAG_VIP = " " + PREFIX_TAG + "VIP";
    public static final String VALID_TAG_DELUXE_ROOM = " " + PREFIX_TAG + "Deluxe Room";
    public static final String TAG_DESC_VIP = " " + PREFIX_TAG + VALID_TAG_VIP;
    public static final String TAG_DESC_DELUXE_ROOM = " " + PREFIX_TAG + VALID_TAG_DELUXE_ROOM;

    // Staff Tags
    public static final String VALID_TAG_SENIOR_STAFF = " " + PREFIX_TAG + "Senior Staff";
    public static final String VALID_TAG_CHEF = " " + PREFIX_TAG + "Chef";
    public static final String TAG_DESC_SENIOR_STAFF = " " + PREFIX_TAG + VALID_TAG_SENIOR_STAFF;
    public static final String TAG_DESC_CHEF = " " + PREFIX_TAG + VALID_TAG_CHEF;

    // Invalid fields
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_STAFF_ID = " " + PREFIX_STAFF_ID + "1#2"; //'@' not allowed in staff id

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditGuestDescriptor DESC_ALICE;
    public static final EditCommand.EditGuestDescriptor DESC_BENSON;
    public static final EditCommand.EditStaffDescriptor DESC_DANIEL;
    public static final EditCommand.EditStaffDescriptor DESC_ELLE;

    static {
        DESC_ALICE = new EditGuestDescriptorBuilder()
                .withName(VALID_NAME_ALICE)
                .withEmail(VALID_EMAIL_ALICE)
                .withTags(VALID_TAG_ALICE)
                .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
                .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
                .build();

        DESC_BENSON = new EditGuestDescriptorBuilder()
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .withTags(VALID_TAG_VIP, VALID_TAG_DELUXE_ROOM)
                .withRoomNumber(VALID_ROOM_NUMBER_BENSON)
                .withPassportNumber(VALID_PASSPORT_NUMBER_BENSON)
                .build();

        DESC_DANIEL = new EditStaffDescriptorBuilder()
                .withName(VALID_NAME_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withTags(VALID_TAG_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withStaffId(VALID_STAFF_ID_DANIEL)
                .build();

        DESC_ELLE = new EditStaffDescriptorBuilder()
                .withName(VALID_NAME_ELLE)
                .withEmail(VALID_EMAIL_ELLE)
                .withTags(VALID_TAG_SENIOR_STAFF)
                .withAddress(VALID_ADDRESS_ELLE)
                .withPhone(VALID_PHONE_ELLE)
                .withStaffId(VALID_STAFF_ID_ELLE)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIdentifier} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        String uniqueIdentifier;

        if (person instanceof Guest) {
            Guest guest = (Guest) person;
            uniqueIdentifier = guest.getPassportNumber().value;
        } else {
            Staff staff = (Staff) person;
            uniqueIdentifier = staff.getStaffId().value;
        }

        model.updateFilteredPersonList(new IdentifierContainsKeywordsPredicate(Arrays.asList(uniqueIdentifier)));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
