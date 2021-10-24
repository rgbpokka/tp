package seedu.address.logic.commands.vendor;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.vendor.VendorBook;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

public class ClearVendorCommandTest {

    @Test
    public void execute_emptyVendorBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearVendorCommand(), model, ClearVendorCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyVendorBook_success() {
        Model model = new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs());
        expectedModel.setVendorBook(new VendorBook());

        assertCommandSuccess(new ClearVendorCommand(), model, ClearVendorCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
