package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Guest;

/**
 * Checks in a guest into the hotel.
 */
public class CheckInReturningGuestCommand extends Command {

    public static final String COMMAND_WORD = "returncheckin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in a returning guest to the hotel. "
            + "Guest Parameters: "
            + PREFIX_PASSPORT_NUMBER + "PASSPORT NUMBER "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROOM_NUMBER + "123";

    public static final String MESSAGE_SUCCESS = "New guest checked in: %1$s";
    public static final String MESSAGE_SUCCESS_RETURNING_GUEST = "Returning guest checked in: %1$s";
    public static final String MESSAGE_DUPLICATE_GUEST = "This guest is already checked in.";
    public static final String MESSAGE_NONEXISTENT_GUEST = "This guest does not belong in our archive.";

    private final Guest toCheckIn;

    /**
     * Creates an CheckInReturningGuestCommand to add the specified {@code Guest}
     */
    public CheckInReturningGuestCommand(Guest guest) {
        requireNonNull(guest);
        toCheckIn = guest;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getArchivedGuest(toCheckIn.getPassportNumber()).isEmpty()) {
            throw new CommandException(MESSAGE_NONEXISTENT_GUEST);
        }

        Guest archivedGuest = model.getArchivedGuest(toCheckIn.getPassportNumber()).get();
        Guest returningGuest = new Guest(archivedGuest.getName(), archivedGuest.getEmail(),
                archivedGuest.getTags(), toCheckIn.getRoomNumber(), archivedGuest.getPassportNumber());
        model.addGuest(returningGuest);
        System.out.println(returningGuest);
        model.deleteArchivedGuest(archivedGuest);
        return new CommandResult(String.format(MESSAGE_SUCCESS_RETURNING_GUEST, returningGuest));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckInReturningGuestCommand // instanceof handles nulls
                && toCheckIn.equals(((CheckInReturningGuestCommand) other).toCheckIn));
    }
}
