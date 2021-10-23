package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DANIEL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditStaffDescriptor;
import seedu.address.testutil.vendor.EditVendorDescriptorBuilder;

public class EditStaffDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStaffDescriptor descriptorWithSameValues = new EditStaffDescriptor(DESC_ELLE);
        assertTrue(DESC_ELLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ELLE.equals(DESC_ELLE));

        // null -> returns false
        assertFalse(DESC_ELLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_ELLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_ELLE.equals(DESC_DANIEL));

        // different name -> returns false
        EditStaffDescriptor editedElle = new EditVendorDescriptorBuilder(DESC_ELLE)
                .withName(VALID_NAME_DANIEL)
                .build();
        assertFalse(DESC_ELLE.equals(editedElle));

        // different email -> returns false
        editedElle = new EditVendorDescriptorBuilder(DESC_ELLE).withEmail(VALID_EMAIL_DANIEL).build();
        assertFalse(DESC_ELLE.equals(editedElle));

        // different tags -> returns false
        editedElle = new EditVendorDescriptorBuilder(DESC_ELLE).withTags(VALID_TAG_DANIEL).build();
        assertFalse(DESC_ELLE.equals(editedElle));

        // different address -> returns false
        editedElle = new EditVendorDescriptorBuilder(DESC_ELLE).withAddress(VALID_ADDRESS_DANIEL).build();
        assertFalse(DESC_ELLE.equals(editedElle));

        // different phone -> returns false
        editedElle = new EditVendorDescriptorBuilder(DESC_ELLE).withPhone(VALID_PHONE_DANIEL).build();
        assertFalse(DESC_ELLE.equals(editedElle));

        // different staff id -> returns false
        editedElle = new EditVendorDescriptorBuilder(DESC_ELLE).withStaffId(VALID_STAFF_ID_DANIEL).build();
        assertFalse(DESC_ELLE.equals(editedElle));
    }
}


