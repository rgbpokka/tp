package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_UNUSED;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandStaffTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validStaffId_success() {
        Staff staffToDelete = (Staff) model.getFilteredPersonList()
                .stream()
                .filter(p -> p instanceof Staff && ((Staff) p).getStaffId().equals(STAFF_ID_FIRST_PERSON))
                .findAny()
                .orElse(null);
        DeleteCommand deleteCommand = new DeleteCommand(STAFF_ID_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, staffToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(staffToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStaffId_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(STAFF_ID_UNUSED);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_UNIQUE_IDENTIFIER);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(STAFF_ID_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(STAFF_ID_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(STAFF_ID_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
