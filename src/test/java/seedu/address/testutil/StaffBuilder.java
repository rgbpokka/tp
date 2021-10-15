package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Staff;
import seedu.address.model.person.StaffId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_DEFAULT;

public class StaffBuilder extends PersonBuilder {
    private Address address;
    private StaffId staffId;
    private Phone phone;

    public static final Address DEFAULT_ADDRESS = new Address("123, Jurong West Ave 6, #08-111");
    public static final StaffId DEFAULT_STAFF_ID = new StaffId(STAFF_ID_DEFAULT.toString());
    public static final Tag DEFAULT_TAG = new Tag("Staff");
    public static final Phone DEFAULT_PHONE = new Phone("85355255");

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        super();
        address = DEFAULT_ADDRESS;
        staffId = DEFAULT_STAFF_ID;
        phone = DEFAULT_PHONE;
        getTags().add(DEFAULT_TAG);
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        super(staffToCopy);
        address = staffToCopy.getAddress();
        staffId = staffToCopy.getStaffId();
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
    public StaffBuilder withTags(String ... tags) {
        setTags(SampleDataUtil.getTagSet(tags));
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
        this.staffId = new StaffId(staffId);
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
        return new Staff(getName(), getEmail(), getTags(), address, staffId, phone);
    }
}