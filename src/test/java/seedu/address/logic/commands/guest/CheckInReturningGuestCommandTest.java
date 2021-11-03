package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.model.guest.UniqueGuestList;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.guest.GuestBuilder;

public class CheckInReturningGuestCommandTest {

    @Test
    public void constructor_nullGuest_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckInReturningGuestCommand(null));
    }

    //    @Test
    //    public void execute_guestAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingGuestAdded modelStub = new ModelStubAcceptingGuestAdded();
    //        Guest validGuest = new GuestBuilder().build();
    //
    //        CommandResult commandResult = new CheckInNewGuestCommand(validGuest).execute(modelStub);
    //
    //        assertEquals(String.format(CheckInNewGuestCommand.MESSAGE_SUCCESS, validGuest),
    //        commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validGuest), modelStub.guestsAdded);
    //    }

    @Test
    public void execute_duplicateGuest_throwsCommandException() {
        Guest validGuest = new GuestBuilder().build();
        CheckInReturningGuestCommand checkInReturningGuestCommand = new CheckInReturningGuestCommand(validGuest);
        ModelStub modelStub = new ModelStubWithGuest(validGuest);

        assertThrows(CommandException.class, CheckInReturningGuestCommand.MESSAGE_NONEXISTENT_GUEST, (
        ) -> checkInReturningGuestCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Guest alice = new GuestBuilder().withName("Alice").build();
        Guest bob = new GuestBuilder().withName("Bob").build();
        CheckInReturningGuestCommand addAliceCommand = new CheckInReturningGuestCommand(alice);
        CheckInReturningGuestCommand addBobCommand = new CheckInReturningGuestCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        CheckInReturningGuestCommand addAliceCommandCopy = new CheckInReturningGuestCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different guest -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single guest.
     */
    private class ModelStubWithGuest extends ModelStub {
        private final Guest guest;
        private final UniqueGuestList uniqueGuestList = new UniqueGuestList();

        ModelStubWithGuest(Guest guest) {
            requireNonNull(guest);
            this.guest = guest;
        }

        @Override
        public boolean hasGuest(Guest guest) {
            requireNonNull(guest);
            return this.guest.isSame(guest);
        }

        @Override
        public Optional<Guest> getArchivedGuest(PassportNumber passportNumber) {
            return uniqueGuestList.get(passportNumber);
        }
    }

    /**
     * A Model stub that always accept the guest being added.
     */
    private class ModelStubAcceptingGuestAdded extends ModelStub {
        final ArrayList<Guest> guestsAdded = new ArrayList<>();

        @Override
        public boolean hasGuest(Guest guest) {
            requireNonNull(guest);
            return guestsAdded.stream().anyMatch(guest::isSame);
        }

        @Override
        public void addGuest(Guest guest) {
            requireNonNull(guest);
            guestsAdded.add(guest);
        }

        @Override
        public ReadOnlyGuestBook getGuestBook() {
            return new GuestBook();
        }
    }

}
