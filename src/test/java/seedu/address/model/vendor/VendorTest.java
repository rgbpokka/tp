package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SENIOR_STAFF;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.vendor.TypicalVendors.FIONA_VENDOR;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.vendor.VendorBuilder;

public class VendorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Vendor person = new VendorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSameVendor() {
        // same object -> returns true
        assertTrue(FIONA_VENDOR.isSameVendor(FIONA_VENDOR));

        // null -> returns false
        assertFalse(FIONA_VENDOR.isSameVendor(null));

        // same SID, all other attributes different -> returns true
        Vendor editedFiona = new VendorBuilder(FIONA_VENDOR)
                .withName(VALID_NAME_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .withTags(VALID_TAG_SENIOR_STAFF)
                .build();

        assertTrue(FIONA_VENDOR.isSameVendor(editedFiona));

        // different SID, all other attributes same -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withStaffId(VALID_STAFF_ID_DANIEL).build();
        assertFalse(FIONA_VENDOR.isSameVendor(editedFiona));

        // SID has trailing spaces, all other attributes same -> returns false
        String idWithTrailingSpaces = VALID_STAFF_ID_DANIEL + "  ";
        Vendor editedDaniel = new VendorBuilder(DANIEL_STAFF).withStaffId(idWithTrailingSpaces).build();
        assertFalse(DANIEL_STAFF.isSameVendor(editedDaniel));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Vendor aliceCopy = new VendorBuilder(FIONA_VENDOR).build();
        assertTrue(FIONA_VENDOR.equals(aliceCopy));

        // same object -> returns true
        assertTrue(FIONA_VENDOR.equals(FIONA_VENDOR));

        // null -> returns false
        assertFalse(FIONA_VENDOR.equals(null));

        // different type -> returns false
        assertFalse(FIONA_VENDOR.equals(5));

        // different person -> returns false
        assertFalse(FIONA_VENDOR.equals(DANIEL_STAFF));

        // different name -> returns false
        Vendor editedFiona = new VendorBuilder(FIONA_VENDOR).withStaffId(VALID_STAFF_ID_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different phone -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withPhone(VALID_PHONE_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different email -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withEmail(VALID_EMAIL_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different address -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withAddress(VALID_ADDRESS_DANIEL).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));

        // different tags -> returns false
        editedFiona = new VendorBuilder(FIONA_VENDOR).withTags(VALID_TAG_SENIOR_STAFF).build();
        assertFalse(FIONA_VENDOR.equals(editedFiona));
    }
}
