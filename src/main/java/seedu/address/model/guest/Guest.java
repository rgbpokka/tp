package seedu.address.model.guest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.Taggable;
import seedu.address.model.uniquelist.UniqueListItem;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Vendor;

public class Guest extends UniqueListItem implements Taggable {

    private final Name name;
    private final Email email;
    private final RoomNumber roomNumber;
    private final PassportNumber passportNumber;
    private List<Vendor> vendorsHired;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Every field other than vendorsHired must be present and not null.
     *
     * @param name
     * @param email
     * @param tags
     * @param roomNumber
     * @param passportNumber
     */
    public Guest(Name name, Email email, Set<Tag> tags, RoomNumber roomNumber, PassportNumber passportNumber) {
        this.name = name;
        this.email = email;
        this.tags.addAll(tags);
        this.roomNumber = roomNumber;
        this.passportNumber = passportNumber;
        this.vendorsHired = new ArrayList<>();
    }

    public Guest(Name name, Email email, Set<Tag> tags, RoomNumber roomNumber, PassportNumber passportNumber, 
                 List<Vendor> vendorsHired) {
        this.name = name;
        this.email = email;
        this.tags.addAll(tags);
        this.roomNumber = roomNumber;
        this.passportNumber = passportNumber;
        this.vendorsHired = vendorsHired;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public PassportNumber getPassportNumber() {
        return passportNumber;
    }

    public List<Vendor> getVendorsHired() {
        return vendorsHired;
    }
    
    public void charge(Vendor vendor) {
        this.vendorsHired.add(vendor);
    }

    @Override
    public boolean isSame(UniqueListItem otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (otherItem instanceof Guest) {
            Guest otherGuest = (Guest) otherItem;
            return otherGuest.getPassportNumber().equals(getPassportNumber());
        }

        return false;
    }

    /**
     * Returns true if both guests have the same identity and data fields.
     * This defines a stronger notion of equality between two guests.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Guest)) {
            return false;
        }

        Guest otherGuest = (Guest) other;
        return otherGuest.getName().equals(getName())
                && otherGuest.getRoomNumber().equals(getRoomNumber())
                && otherGuest.getEmail().equals(getEmail())
                && otherGuest.getPassportNumber().equals(getPassportNumber())
                && otherGuest.getTags().equals(getTags())
                && otherGuest.getVendorsHired().equals(getVendorsHired());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, roomNumber, passportNumber, tags, vendorsHired);
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
