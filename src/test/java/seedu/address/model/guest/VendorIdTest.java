package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.vendor.VendorId;

public class VendorIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new VendorId(null));
    }

    @Test
    public void constructor_invalidStaffId_throwsIllegalArgumentException() {
        String invalidStaffId = "";
        assertThrows(IllegalArgumentException.class, () -> new VendorId(invalidStaffId));
    }

    @Test
    public void isValidStaffId() {
        // null staff id
        assertThrows(NullPointerException.class, () -> VendorId.isValidStaffId(null));

        // invalid staff id
        assertFalse(VendorId.isValidStaffId("")); // empty string
        assertFalse(VendorId.isValidStaffId("^")); // only non-alphanumeric characters
        assertFalse(VendorId.isValidStaffId("peter*")); // contains non-alphanumeric characters
        assertFalse(VendorId.isValidStaffId("  ")); // spaces only

        // valid staff id
        assertTrue(VendorId.isValidStaffId("peter jack")); // alphabets only
        assertTrue(VendorId.isValidStaffId("12345")); // numbers only
        assertTrue(VendorId.isValidStaffId("peter the 2nd")); // alphanumeric characters
        assertTrue(VendorId.isValidStaffId("Capital Tan")); // with capital letters
        assertTrue(VendorId.isValidStaffId("David Roger Jackson Ray Jr 2nd")); // long staff id

    }


}
