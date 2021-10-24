package seedu.address.logic.commands.vendor;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Archive;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteVendorCommand extends Command {

    public static final String COMMAND_WORD = "deletevendor";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deleted the vendor by the vendor ID in the displayed vendor list.\n"
            + "Parameters: Vendor ID\n"
            + "Example: " + COMMAND_WORD + " vid/123";

    public static final String MESSAGE_DELETE_SUCCESSFUL = "Deleted Vendor: %1$s";

    private final VendorId vendorId;

    public DeleteVendorCommand(VendorId vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Vendor> lastShownList = model.getFilteredVendorList();

        Vendor vendorToBeDeleted =
                lastShownList.stream().filter(p -> p.getVendorId().equals(vendorId)).findAny().orElse(null);

        if (vendorToBeDeleted == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_VENDORID);
        }

        model.deleteVendor(vendorToBeDeleted);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESSFUL, vendorToBeDeleted));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteVendorCommand // instanceof handles nulls
                && vendorId.equals(((DeleteVendorCommand) other).vendorId)); // state check
    }
    
}
