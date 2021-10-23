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
import static seedu.address.testutil.guest.TypicalGuests.DANIEL_STAFF;
import static seedu.address.testutil.guest.TypicalGuests.FIONA_STAFF;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.vendor.VendorBuilder;

public class VendorTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new VendorBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(FIONA_STAFF.isSamePerson(FIONA_STAFF));

        // null -> returns false
        assertFalse(FIONA_STAFF.isSamePerson(null));

        // same SID, all other attributes different -> returns true
        Person editedFiona = new VendorBuilder(FIONA_STAFF)
                .withName(VALID_NAME_DANIEL)
                .withPhone(VALID_PHONE_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .withAddress(VALID_ADDRESS_DANIEL)
                .withTags(VALID_TAG_SENIOR_STAFF)
                .build();

        assertTrue(FIONA_STAFF.isSamePerson(editedFiona));

        // different SID, all other attributes same -> returns false
        editedFiona = new VendorBuilder(FIONA_STAFF).withStaffId(VALID_STAFF_ID_DANIEL).build();
        assertFalse(FIONA_STAFF.isSamePerson(editedFiona));

        // SID has trailing spaces, all other attributes same -> returns false
        String idWithTrailingSpaces = VALID_STAFF_ID_DANIEL + "  ";
        Person editedDaniel = new VendorBuilder(DANIEL_STAFF).withStaffId(idWithTrailingSpaces).build();
        assertFalse(DANIEL_STAFF.isSamePerson(editedDaniel));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new VendorBuilder(FIONA_STAFF).build();
        assertTrue(FIONA_STAFF.equals(aliceCopy));

        // same object -> returns true
        assertTrue(FIONA_STAFF.equals(FIONA_STAFF));

        // null -> returns false
        assertFalse(FIONA_STAFF.equals(null));

        // different type -> returns false
        assertFalse(FIONA_STAFF.equals(5));

        // different person -> returns false
        assertFalse(FIONA_STAFF.equals(DANIEL_STAFF));

        // different name -> returns false
        Person editedFiona = new VendorBuilder(FIONA_STAFF).withStaffId(VALID_STAFF_ID_DANIEL).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different phone -> returns false
        editedFiona = new VendorBuilder(FIONA_STAFF).withPhone(VALID_PHONE_DANIEL).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different email -> returns false
        editedFiona = new VendorBuilder(FIONA_STAFF).withEmail(VALID_EMAIL_DANIEL).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different address -> returns false
        editedFiona = new VendorBuilder(FIONA_STAFF).withAddress(VALID_ADDRESS_DANIEL).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different tags -> returns false
        editedFiona = new VendorBuilder(FIONA_STAFF).withTags(VALID_TAG_SENIOR_STAFF).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));
    }
}
