package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StaffIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StaffId(null));
    }

    @Test
    public void constructor_invalidStaffId_throwsIllegalArgumentException() {
        String invalidStaffId = "";
        assertThrows(IllegalArgumentException.class, () -> new StaffId(invalidStaffId));
    }

    @Test
    public void isValidStaffId() {
        // null staff id
        assertThrows(NullPointerException.class, () -> StaffId.isValidStaffId(null));

        // invalid staff id
        assertFalse(StaffId.isValidStaffId("")); // empty string
        assertFalse(StaffId.isValidStaffId("^")); // only non-alphanumeric characters
        assertFalse(StaffId.isValidStaffId("peter*")); // contains non-alphanumeric characters
        assertFalse(StaffId.isValidStaffId("  ")); // spaces only

        // valid staff id
        assertTrue(StaffId.isValidStaffId("peter jack")); // alphabets only
        assertTrue(StaffId.isValidStaffId("12345")); // numbers only
        assertTrue(StaffId.isValidStaffId("peter the 2nd")); // alphanumeric characters
        assertTrue(StaffId.isValidStaffId("Capital Tan")); // with capital letters
        assertTrue(StaffId.isValidStaffId("David Roger Jackson Ray Jr 2nd")); // long staff id

    }


}
