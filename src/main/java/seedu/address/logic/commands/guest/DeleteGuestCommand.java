package seedu.address.logic.commands.guest;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteGuestCommand extends Command {

    public static final String COMMAND_WORD = "deleteguest";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the guest by the passport number in the displayed guest list.\n"
            + "Parameters: Passport Number\n"
            + "Example: " + COMMAND_WORD + " pn/A021231B";

    public static final String MESSAGE_DELETE_SUCCESSFUL = "Deleted Guest: %1$s";

    private final PassportNumber passportNumber;

    public DeleteGuestCommand(PassportNumber passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();

        Guest guestToDelete =
                lastShownList.stream().filter(p -> p.getPassportNumber().equals(passportNumber)).findAny().orElse(null);

        if (guestToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_PASSPORT_NUMBER);
        }

        model.deleteGuest(guestToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESSFUL, guestToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteGuestCommand // instanceof handles nulls
                && passportNumber.equals(((DeleteGuestCommand) other).passportNumber)); // state check
    }
    
}
