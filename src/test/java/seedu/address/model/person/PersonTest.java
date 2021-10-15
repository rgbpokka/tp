package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.DANIEL_STAFF;
import static seedu.address.testutil.TypicalPersons.FIONA_STAFF;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StaffBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new StaffBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(FIONA_STAFF.isSamePerson(FIONA_STAFF));

        // null -> returns false
        assertFalse(FIONA_STAFF.isSamePerson(null));

        // same SID, all other attributes different -> returns true
        Person editedFiona =
                new StaffBuilder(FIONA_STAFF).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withEmail(
                                VALID_EMAIL_BOB)
                        .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(FIONA_STAFF.isSamePerson(editedFiona));

        // different SID, all other attributes same -> returns false
        editedFiona = new StaffBuilder(FIONA_STAFF).withStaffId(VALID_SID_BOB).build();
        assertFalse(FIONA_STAFF.isSamePerson(editedFiona));

        // name differs in case, all other attributes same -> returns false
        //        Person editedBob = new StaffBuilder(FIONA_STAFF).withStaffId(VALID_NAME_BOB.toLowerCase()).build();
        //        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        //        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        //        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        //        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new StaffBuilder(FIONA_STAFF).build();
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
        Person editedFiona = new StaffBuilder(FIONA_STAFF).withStaffId(VALID_SID_BOB).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different phone -> returns false
        editedFiona = new StaffBuilder(FIONA_STAFF).withPhone(VALID_PHONE_BOB).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different email -> returns false
        editedFiona = new StaffBuilder(FIONA_STAFF).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different address -> returns false
        editedFiona = new StaffBuilder(FIONA_STAFF).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));

        // different tags -> returns false
        editedFiona = new StaffBuilder(FIONA_STAFF).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(FIONA_STAFF.equals(editedFiona));
    }
}
