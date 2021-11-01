package seedu.address.logic.commands.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

public class DeleteVendorCommand extends Command {

    public static final String COMMAND_WORD = "deletevendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the vendor by the vendor ID.\n"
            + "Parameters: "
            + PREFIX_VENDOR_ID + "VENDOR_ID\n"
            + "Example: " + COMMAND_WORD + " vid/123";

    public static final String MESSAGE_DELETE_SUCCESSFUL = "Deleted Vendor: %1$s";

    private final VendorId vendorId;

    /**
     * Constructs DeleteVendorCommand.
     *
     * @param vendorId Vendor ID for vendor to be deleted.
     */
    public DeleteVendorCommand(VendorId vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Vendor> vendorToBeDeleted = model.getVendor(this.vendorId);

        if (vendorToBeDeleted.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENDORID);
        }

        model.deleteVendor(vendorToBeDeleted.get());
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESSFUL, vendorToBeDeleted.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteVendorCommand // instanceof handles nulls
                && vendorId.equals(((DeleteVendorCommand) other).vendorId)); // state check
    }
}
