package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.commonattributes.Email;
import seedu.address.model.guest.Guest;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.GuestManager;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.ReadOnlyGuestManager;
import seedu.address.model.guest.RoomNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorManager;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Guest[] getSampleGuests() {
        return new Guest[]{
            new Guest(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), getTagSet("friends", "Guest"),
                    new RoomNumber("123"), new PassportNumber("EC4744643")),
            new Guest(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                    getTagSet("colleagues", "friends", "Guest"),
                    new RoomNumber("456"), new PassportNumber("FG4741690")),
        };
    }

    public static Vendor[] getSampleVendors() {
        return new Vendor[]{
        };
    } 

    public static ReadOnlyGuestManager getSampleGuestManager() {
        GuestManager sampleGuestManager = new GuestManager();
        for (Guest sampleGuest : getSampleGuests()) {
            sampleGuestManager.addGuest(sampleGuest);
        }
        return sampleGuestManager;
    }

    public static ReadOnlyVendorManager getSampleVendorManager() {
        VendorManager sampleVendorManager = new VendorManager();
        for (Vendor sampleVendor: getSampleVendors()) {
            sampleVendorManager.addVendor(sampleVendor);
        }
        return sampleVendorManager;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
