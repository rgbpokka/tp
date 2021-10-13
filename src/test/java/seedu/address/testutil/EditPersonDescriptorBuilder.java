package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.PassportNumber;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.Staff;
import seedu.address.model.person.StaffId;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setEmail(person.getEmail());
        descriptor.setTags(person.getTags());
        
        if (person instanceof Guest) {
            Guest guest = (Guest) person;
            descriptor.setPassportNumber(guest.getPassportNumber());
            descriptor.setRoomNumber((guest.getRoomNumber()));
        }

        if (person instanceof Staff) {
            Staff staff = (Staff) person;
            descriptor.setPhone(staff.getPhone());
            descriptor.setAddress(staff.getAddress());
            descriptor.setStaffId(staff.getStaffId());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }
    
    /**
     * Sets the {@code RoomNumber} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRoomNumber(String roomNumber) {
        descriptor.setRoomNumber(new RoomNumber(roomNumber));
        return this;
    }

    /**
     * Sets the {@code PassportNumber} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPassportNumber(String passportNumber) {
        descriptor.setPassportNumber(new PassportNumber(passportNumber));
        return this;
    }

    /**
     * Sets the {@code StaffId} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withStaffId(String staffId) {
        descriptor.setStaffId(new StaffId(staffId));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
