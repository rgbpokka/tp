package seedu.address.logic.commands.guest;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class DeleteGuestCommand extends Command {

    public static final String COMMAND_WORD = "deleteguest";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete the guest by the passport number from the list of checked in guests or the archive.\n"
            + "Parameters: Passport Number\n"
            + "Example: " + COMMAND_WORD + " pn/A021231B";

    public static final String MESSAGE_DELETE_SUCCESSFUL = "Deleted Guest: %1$s";

    private final PassportNumber passportNumber;

    public DeleteGuestCommand(PassportNumber passportNumber) {
        assert passportNumber != null;
        this.passportNumber = passportNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Guest> guestToBeDeletedFromCheckInList = getGuestFromCheckInList(model);
        Optional<Guest> guestToBeDeletedFromArchive = getGuestFromArchive(model);

        if (guestToBeDeletedFromArchive.isEmpty() && guestToBeDeletedFromCheckInList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_PASSPORT_NUMBER);
        }

        // Guest should not be found in both archive and checkin
        assert (guestToBeDeletedFromCheckInList.isPresent() && guestToBeDeletedFromArchive.isPresent()) == false;



        return deleteGuest(model, guestToBeDeletedFromArchive, guestToBeDeletedFromCheckInList);
    }

    private Optional<Guest> getGuestFromCheckInList(Model model) {
        Optional<Guest> guestToBeDeleted = model.getGuest(this.passportNumber);

        return guestToBeDeleted;
    }

    private Optional<Guest> getGuestFromArchive(Model model) {
        Optional<Guest> guestToBeDeleted = model.getArchivedGuest(this.passportNumber);

        return guestToBeDeleted;
    }

    private CommandResult deleteGuest(Model model, Optional<Guest> archiveGuest, Optional<Guest> checkedInGuest) throws CommandException {
        assert archiveGuest != null || checkedInGuest != null;

        if (archiveGuest.isPresent()) {
            Guest guest = archiveGuest.get();
            model.deleteArchivedGuest(guest);
            return new CommandResult(String.format(MESSAGE_DELETE_SUCCESSFUL, guest));
        } else {
            Guest guest = checkedInGuest.get();
            model.deleteGuest(guest);
            return new CommandResult(String.format(MESSAGE_DELETE_SUCCESSFUL, guest));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGuestCommand // instanceof handles nulls
                && passportNumber.equals(((DeleteGuestCommand) other).passportNumber)); // state check
    }
    
}
