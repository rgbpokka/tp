package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGuestAtPassportNumber;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_UNUSED;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.BENSON_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.address.logic.commands.guest.ClearGuestCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Guest;
import seedu.address.testutil.guest.EditGuestDescriptorBuilder;
import seedu.address.testutil.guest.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandGuestTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Guest guest = ALICE_GUEST;
        Guest editedGuest = new GuestBuilder().build();
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(editedGuest).build();
        EditCommand editCommand = new EditCommand(guest.getPassportNumber(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(guest, editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Guest guest = ALICE_GUEST;

        GuestBuilder guestBuilder = new GuestBuilder(guest);
        Person editedGuest = guestBuilder
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .build();

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .build();

        EditCommand editCommand = new EditCommand(guest.getPassportNumber(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(guest, editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        // no fields are changed, so the edited guest stays exactly the same
        Guest editedGuest = ALICE_GUEST;

        EditCommand editCommand = new EditCommand(editedGuest.getPassportNumber(), new EditGuestDescriptor());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showGuestAtPassportNumber(model, INDEX_FIRST_PERSON);

        Guest personInFilteredList = ALICE_GUEST;
        Person editedGuest = new GuestBuilder(personInFilteredList)
                .withName(VALID_NAME_BENSON)
                .build();
        EditCommand editCommand = new EditCommand(
                personInFilteredList.getPassportNumber(),
                new EditGuestDescriptorBuilder().withName(VALID_NAME_BENSON).build()
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateGuestUnfilteredList_failure() {
        Guest guest = ALICE_GUEST;
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(guest).build();
        EditCommand editCommand = new EditCommand(BENSON_GUEST.getPassportNumber(), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateGuestFilteredList_failure() {
        showGuestAtPassportNumber(model, INDEX_FIRST_PERSON);

        Guest personInList = BENSON_GUEST;
        EditCommand editCommand = new EditCommand(ALICE_GUEST.getPassportNumber(),
                new EditGuestDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPassportNumberUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(PASSPORT_NUMBER_UNUSED, new EditGuestDescriptor());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_UNIQUE_IDENTIFIER);
    }

    //    @Test
    //    public void execute_invalidPassportNumberFilteredList_failure() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //        Index outOfBoundIndex = INDEX_SECOND_PERSON;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
    //
    //        EditCommand editCommand = new EditCommand(outOfBoundIndex,
    //                new EditPersonDescriptorBuilder().withName(VALID_NAME_BENSON).build());
    //
    //        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(PASSPORT_NUMBER_FIRST_PERSON, DESC_ALICE);

        // same values -> returns true
        EditGuestDescriptor copyDescriptor = new EditGuestDescriptor(DESC_ALICE);
        EditCommand commandWithSameValues = new EditCommand(PASSPORT_NUMBER_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearGuestCommand()));

        // different passport number -> returns false
        assertFalse(standardCommand.equals(new EditCommand(PASSPORT_NUMBER_SECOND_PERSON, DESC_ALICE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(PASSPORT_NUMBER_FIRST_PERSON, DESC_BENSON)));
    }

}
