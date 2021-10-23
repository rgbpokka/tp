package seedu.address.testutil.vendor;

import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GEORGE;

/**
 * A utility class containing a list of {@code Vendor} objects to be used in tests.
 */
public class TypicalVendors {

    public static final Vendor DANIEL_VENDOR = new VendorBuilder()
            .withName(VALID_NAME_DANIEL)
            .withEmail(VALID_EMAIL_DANIEL)
            .withTags(VALID_TAG_DANIEL)
            .withAddress(VALID_ADDRESS_DANIEL)
            .withPhone(VALID_PHONE_DANIEL)
            .withStaffId(VALID_STAFF_ID_DANIEL)
            .with
            .build();

    public static final Vendor ELLE_VENDOR = new VendorBuilder()
            .withName(VALID_NAME_ELLE)
            .withEmail(VALID_EMAIL_ELLE)
            .withTags(VALID_TAG_ELLE)
            .withAddress(VALID_ADDRESS_ELLE)
            .withPhone(VALID_PHONE_ELLE)
            .withStaffId(VALID_STAFF_ID_ELLE)
            .build();

    public static final Vendor FIONA_VENDOR = new VendorBuilder()
            .withName(VALID_NAME_FIONA)
            .withEmail(VALID_EMAIL_FIONA)
            .withTags(VALID_TAG_FIONA)
            .withAddress(VALID_ADDRESS_FIONA)
            .withPhone(VALID_PHONE_FIONA)
            .withStaffId(VALID_STAFF_ID_FIONA)
            .build();

    public static final Vendor GEORGE_VENDOR = new VendorBuilder()
            .withName(VALID_NAME_GEORGE)
            .withEmail(VALID_EMAIL_GEORGE)
            .withTags(VALID_TAG_GEORGE)
            .withAddress(VALID_ADDRESS_GEORGE)
            .withPhone(VALID_PHONE_GEORGE)
            .withStaffId(VALID_STAFF_ID_GEORGE)
            .build();

    private TypicalVendors() {
    } // prevents instantiation

    /**
     * Returns an {@code VendorBook} with all the typical vendors.
     */
    public static VendorBook getTypicalVendorBook() {
        VendorBook vendorBook = new VendorBook();
        for (Vendor vendor : getTypicalVendors()) {
            vendorBook.addVendor(vendor);
        }
        return vendorBook;
    }

    public static List<Vendor> getTypicalVendors() {
        return new ArrayList<>(Arrays.asList(DANIEL_VENDOR, ELLE_VENDOR, FIONA_VENDOR, GEORGE_VENDOR));
    }
}
