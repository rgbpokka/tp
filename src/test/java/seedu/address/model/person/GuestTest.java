package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.GuestBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SENIOR_STAFF;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE_GUEST;
import static seedu.address.testutil.TypicalPersons.BENSON_GUEST;

public class GuestTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new GuestBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE_GUEST.isSamePerson(ALICE_GUEST));

        // null -> returns false
        assertFalse(ALICE_GUEST.isSamePerson(null));

        // same passport number, all other attributes different -> returns true
        Person editedFiona = new GuestBuilder(ALICE_GUEST)
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .withRoomNumber(VALID_ROOM_NUMBER_BENSON)
                .withTags(VALID_TAG_SENIOR_STAFF)
                .build();

        assertTrue(ALICE_GUEST.isSamePerson(editedFiona));

        // different passport number, all other attributes same -> returns false
        Person editedAlice = new GuestBuilder(ALICE_GUEST).withPassportNumber(VALID_PASSPORT_NUMBER_BENSON).build();
        assertFalse(ALICE_GUEST.isSamePerson(editedAlice));

        // Passport number has trailing spaces, all other attributes same -> returns false
        String passportWithTrailingSpaces = VALID_PASSPORT_NUMBER_BENSON + "  ";
        editedAlice = new GuestBuilder(ALICE_GUEST).withPassportNumber(passportWithTrailingSpaces).build();
        assertFalse(ALICE_GUEST.isSamePerson(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new GuestBuilder(ALICE_GUEST).build();
        assertTrue(ALICE_GUEST.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE_GUEST.equals(ALICE_GUEST));

        // null -> returns false
        assertFalse(ALICE_GUEST.equals(null));

        // different type -> returns false
        assertFalse(ALICE_GUEST.equals(5));

        // different person -> returns false
        assertFalse(ALICE_GUEST.equals(BENSON_GUEST));

        // different passport number -> returns false
        Person editedFiona = new GuestBuilder(ALICE_GUEST).withPassportNumber(VALID_PASSPORT_NUMBER_BENSON).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));

        // different room number -> returns false
        editedFiona = new GuestBuilder(ALICE_GUEST).withRoomNumber(VALID_ROOM_NUMBER_BENSON).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));

        // different email -> returns false
        editedFiona = new GuestBuilder(ALICE_GUEST).withEmail(VALID_EMAIL_BENSON).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));

        // different tags -> returns false
        editedFiona = new GuestBuilder(ALICE_GUEST).withTags(VALID_TAG_SENIOR_STAFF).build();
        assertFalse(ALICE_GUEST.equals(editedFiona));
    } 
    
    
}
