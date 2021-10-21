package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalPersons.DANIEL_STAFF;
import static seedu.address.testutil.TypicalPersons.ELLE_STAFF;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_UNUSED;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditStaffDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.StaffId;
import seedu.address.model.person.UniqueIdentifier;
import seedu.address.testutil.EditStaffDescriptorBuilder;
import seedu.address.testutil.StaffBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandStaffTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Staff staff = DANIEL_STAFF;
        Staff editedStaff = new StaffBuilder().build();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(editedStaff).build();
        EditCommand editCommand = new EditCommand(staff.getStaffId(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStaff);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(staff, editedStaff);
        
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Staff staff = DANIEL_STAFF;

        StaffBuilder staffBuilder = new StaffBuilder(staff);
        Person editedStaff = staffBuilder
                .withName(VALID_NAME_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .build();

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder()
                .withName(VALID_NAME_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .build();

        EditCommand editCommand = new EditCommand(staff.getStaffId(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStaff);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(staff, editedStaff);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        // no fields are changed, so the edited staff stays exactly the same
        UniqueIdentifier targetIdentifier = new StaffId(VALID_STAFF_ID_DANIEL);
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptorBuilder().withStaffId(VALID_STAFF_ID_DANIEL).build();
        EditCommand editCommand = new EditCommand(targetIdentifier, editStaffDescriptor);
        
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, DANIEL_STAFF);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // Hard-coded to show first Staff in TypicalPersons list
        showPersonAtIndex(model, Index.fromZeroBased(3));

        Staff personInFilteredList = DANIEL_STAFF;
        Person editedStaff = new StaffBuilder(personInFilteredList)
                .withName(VALID_NAME_DANIEL)
                .build();
        EditCommand editCommand = new EditCommand(
                personInFilteredList.getStaffId(),
                new EditStaffDescriptorBuilder().withName(VALID_NAME_DANIEL).build()
        );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStaff);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personInFilteredList, editedStaff);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStaffUnfilteredList_failure() {
        Staff staff = DANIEL_STAFF;
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(staff).build();
        EditCommand editCommand = new EditCommand(ELLE_STAFF.getStaffId(), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateStaffFilteredList_failure() {
        // Hard-coded to show first Staff in TypicalPersons list
        showPersonAtIndex(model, Index.fromZeroBased(3));

        Staff personInList = ELLE_STAFF;
        EditCommand editCommand = new EditCommand(DANIEL_STAFF.getStaffId(),
                new EditStaffDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidStaffIdUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(STAFF_ID_UNUSED, new EditStaffDescriptor());

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
    //                new EditPersonDescriptorBuilder().withName(VALID_NAME_DANIEL).build());
    //
    //        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        // Something needs to be done about DESC_ELLE?? Change to something else??
        final EditCommand standardCommand = new EditCommand(STAFF_ID_FIRST_PERSON, DESC_ELLE);

        // same values -> returns true
        EditStaffDescriptor copyDescriptor = new EditStaffDescriptor(DESC_ELLE);
        EditCommand commandWithSameValues = new EditCommand(STAFF_ID_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different staff id -> returns false
        assertFalse(standardCommand.equals(new EditCommand(STAFF_ID_SECOND_PERSON, DESC_ELLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(STAFF_ID_FIRST_PERSON, DESC_DANIEL)));
    }

}
