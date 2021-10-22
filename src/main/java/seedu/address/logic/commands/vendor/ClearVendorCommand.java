package seedu.address.logic.commands.vendor;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.vendor.VendorManager;

import static java.util.Objects.requireNonNull;

public class ClearVendorCommand extends Command {

    public static final String COMMAND_WORD = "clearvendor";
    public static final String MESSAGE_SUCCESS = "Vendor list has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setVendorManager(new VendorManager());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
