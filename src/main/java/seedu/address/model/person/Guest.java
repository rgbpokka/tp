package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Objects;
import java.util.Set;

public class Guest extends Person {
    
    private final RoomNumber roomNumber;
    private final PassportNumber passportNumber;
    private final Tag guestTag = new Tag("guest");
    
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param email
     * @param tags
     * @param roomNumber 
     * @param passportNumber 
     * 
     */
    public Guest(Name name, Email email, Set<Tag> tags, RoomNumber roomNumber, PassportNumber passportNumber) {
        super(name, email, tags);
//        getTags().add(guestTag);
        this.roomNumber = roomNumber;
        this.passportNumber = passportNumber;
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public PassportNumber getPassportNumber() {
        return passportNumber;
    }

    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (otherPerson instanceof Guest) {
            Guest otherGuest = (Guest) otherPerson;
            return otherGuest.getPassportNumber().equals(getPassportNumber());
        }

        return false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Guest)) {
            return false;
        }

        Guest otherPerson = (Guest) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getRoomNumber().equals(getRoomNumber())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getPassportNumber().equals(getPassportNumber())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getEmail(), getRoomNumber(), getPassportNumber(), getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; RoomNumber: ")
                .append(getRoomNumber())
                .append("; PassportNumber: ")
                .append(getPassportNumber());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
    
}
