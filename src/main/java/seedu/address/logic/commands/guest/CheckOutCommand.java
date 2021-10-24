package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.PassportNumber;

import java.util.List;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class CheckOutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks out the guest by the passport number in the displayed guest list.\n"
            + "Parameters: Passport Number\n"
            + "Example: " + COMMAND_WORD + " pn/A021231B";

    public static final String MESSAGE_CHECKOUT_SUCCESSFUL = "Checked out Guest: %1$s";

    private final PassportNumber passportNumber;

    public CheckOutCommand(PassportNumber passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Guest> lastShownList = model.getFilteredGuestList();

        Guest guestToCheckOut =
                lastShownList.stream().filter(p -> p.getPassportNumber().equals(passportNumber)).findAny().orElse(null);

        if (guestToCheckOut == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GUEST_PASSPORT_NUMBER);
        }

        model.deleteGuest(guestToCheckOut); // removes the guest from the guest book
        model.getArchive().addGuest(guestToCheckOut); // adds the guest to the archive

        return new CommandResult(String.format(MESSAGE_CHECKOUT_SUCCESSFUL, guestToCheckOut));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckOutCommand // instanceof handles nulls
                && passportNumber.equals(((CheckOutCommand) other).passportNumber)); // state check
    }
}
