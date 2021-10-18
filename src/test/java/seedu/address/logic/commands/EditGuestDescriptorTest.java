package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_VIP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.address.testutil.EditGuestDescriptorBuilder;

public class EditGuestDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditGuestDescriptor descriptorWithSameValues = new EditGuestDescriptor(DESC_ALICE);
        assertTrue(DESC_ALICE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ALICE.equals(DESC_ALICE));

        // null -> returns false
        assertFalse(DESC_ALICE.equals(null));

        // different types -> returns false
        assertFalse(DESC_ALICE.equals(5));

        // different values -> returns false
        assertFalse(DESC_ALICE.equals(DESC_BENSON));

        // different name -> returns false
        EditGuestDescriptor editedAmy = new EditGuestDescriptorBuilder(DESC_ALICE).withName(VALID_NAME_BENSON).build();
        assertFalse(DESC_ALICE.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_ALICE).withEmail(VALID_EMAIL_BENSON).build();
        assertFalse(DESC_ALICE.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_ALICE).withTags(VALID_TAG_VIP).build();
        assertFalse(DESC_ALICE.equals(editedAmy));

        // different passport number -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_ALICE).withPassportNumber(VALID_PASSPORT_NUMBER_BENSON).build();
        assertFalse(DESC_ALICE.equals(editedAmy));

        // different room number -> returns false
        editedAmy = new EditGuestDescriptorBuilder(DESC_ALICE).withRoomNumber(VALID_ROOM_NUMBER_BENSON).build();
        assertFalse(DESC_ALICE.equals(editedAmy));
    }
}

