package seedu.address.testutil;

import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_DEFAULT;

import java.util.Set;

import seedu.address.model.vendor.Address;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.VendorId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

public class StaffBuilder extends PersonBuilder {

    public static final Address DEFAULT_ADDRESS = new Address("123, Jurong West Ave 6, #08-111");
    public static final VendorId DEFAULT_STAFF_ID = new VendorId(STAFF_ID_DEFAULT.toString());
    public static final Tag DEFAULT_TAG = new Tag("Staff");
    public static final Phone DEFAULT_PHONE = new Phone("85355255");
    private Address address;
    private VendorId vendorId;
    private Phone phone;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        super();
        address = DEFAULT_ADDRESS;
        vendorId = DEFAULT_STAFF_ID;
        phone = DEFAULT_PHONE;
        getTags().add(DEFAULT_TAG);
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        super(staffToCopy);
        address = staffToCopy.getAddress();
        vendorId = staffToCopy.getStaffId();
        phone = staffToCopy.getPhone();
    }

    /**
     * Sets the {@code Name} of the {@code Staff} that we are building.
     */
    public StaffBuilder withName(String name) {
        setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Staff} that we are building.
     */
    public StaffBuilder withTags(String... tags) {
        Set<Tag> staffTag = SampleDataUtil.getTagSet(tags);
        staffTag.add(new Tag("Staff"));
        setTags(staffTag);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Staff} that we are building.
     */
    public StaffBuilder withEmail(String email) {
        setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Staff} that we are building.
     */
    public StaffBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code StaffId} of the {@code Staff} that we are building.
     */
    public StaffBuilder withStaffId(String staffId) {
        this.vendorId = new VendorId(staffId);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Staff} that we are building.
     */
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public Staff build() {
        return new Staff(getName(), getEmail(), getTags(), address, vendorId, phone);
    }
}
