package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CHARLIE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditStaffDescriptor;
import seedu.address.testutil.EditStaffDescriptorBuilder;

public class EditStaffDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStaffDescriptor descriptorWithSameValues = new EditStaffDescriptor(DESC_CHARLIE);
        assertTrue(DESC_CHARLIE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHARLIE.equals(DESC_CHARLIE));

        // null -> returns false
        assertFalse(DESC_CHARLIE.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHARLIE.equals(5));

        // different values -> returns false
        assertFalse(DESC_CHARLIE.equals(DESC_DANIEL));

        // different name -> returns false
        EditStaffDescriptor editedCharlie = new EditStaffDescriptorBuilder(DESC_CHARLIE).withName(VALID_NAME_DANIEL).build();
        assertFalse(DESC_CHARLIE.equals(editedCharlie));

        // different email -> returns false
        editedCharlie = new EditStaffDescriptorBuilder(DESC_CHARLIE).withEmail(VALID_EMAIL_DANIEL).build();
        assertFalse(DESC_CHARLIE.equals(editedCharlie));

        // different tags -> returns false
        editedCharlie = new EditStaffDescriptorBuilder(DESC_CHARLIE).withTags(VALID_TAG_FRIEND).build();
        assertFalse(DESC_CHARLIE.equals(editedCharlie));

        // different address -> returns false
        editedCharlie = new EditStaffDescriptorBuilder(DESC_CHARLIE).withAddress(VALID_ADDRESS_DANIEL).build();
        assertFalse(DESC_CHARLIE.equals(editedCharlie));

        // different phone -> returns false
        editedCharlie = new EditStaffDescriptorBuilder(DESC_CHARLIE).withPhone(VALID_PHONE_DANIEL).build();
        assertFalse(DESC_CHARLIE.equals(editedCharlie));

        // different staff id -> returns false
        editedCharlie = new EditStaffDescriptorBuilder(DESC_CHARLIE).withStaffId(VALID_STAFF_ID_DANIEL).build();
        assertFalse(DESC_CHARLIE.equals(editedCharlie));
    }
}


