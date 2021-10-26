package seedu.address.model.guest;

import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Tests that a {@code Guest} matches the {@code PassportNumber}, {@code RoomNumber}, {@code Name}, {@code Email},
 * and list of {@code Tag} given.
 */
public class GuestPredicate implements Predicate<Guest> {

    private final Optional<PassportNumber> passportNumberOptional;
    private final Optional<RoomNumber> roomNumberOptional;
    private final Optional<Name> nameOptional;
    private final Optional<Email> emailOptional;
    private final Optional<Set<Tag>> tagsOptional;

    public GuestPredicate(Optional<PassportNumber> passportNumberOptional,
                          Optional<RoomNumber> roomNumberOptional,
                          Optional<Name> nameOptional,
                          Optional<Email> emailOptional,
                          Optional<Set<Tag>> tagsOptional) {
        requireAllNonNull(passportNumberOptional, roomNumberOptional, nameOptional, emailOptional, tagsOptional);
        if (tagsOptional.isPresent()) {
            this.tagsOptional = Optional.of(new HashSet<>(tagsOptional.get()));
        } else {
            this.tagsOptional = Optional.empty();
        }
        this.passportNumberOptional = passportNumberOptional;
        this.roomNumberOptional = roomNumberOptional;
        this.nameOptional = nameOptional;
        this.emailOptional = emailOptional;
    }

    @Override
    public boolean test(Guest guest) {
        requireNonNull(guest);
        return testForEmail(guest) && testForName(guest) && testForTags(guest) && testForRoomNumber(guest) &&
                testForPassportNumber(guest);
    }

    private boolean testForPassportNumber(Guest guest) {
        if (passportNumberOptional.isPresent()) {
            return passportNumberOptional.get().equals(guest.getPassportNumber());
        }
        return true;
    }

    private boolean testForName(Guest guest) {
        if (nameOptional.isPresent()) {
            return nameOptional.get().equals(guest.getName());
        }
        return true;
    }

    private boolean testForEmail(Guest guest) {
        if (emailOptional.isPresent()) {
            return emailOptional.get().equals(guest.getEmail());
        }
        return true;
    }

    private boolean testForRoomNumber(Guest guest) {
        if (roomNumberOptional.isPresent()) {
            return roomNumberOptional.get().equals(guest.getRoomNumber());
        }
        return true;
    }

    private boolean testForTags(Guest guest) {
        if (tagsOptional.isPresent()) {
            return tagsOptional.get().stream()
                    .anyMatch(tag -> guest.getTags().contains(tag));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GuestPredicate // instanceof handles nulls
                && emailOptional.equals(((GuestPredicate) other).emailOptional)
                && nameOptional.equals(((GuestPredicate) other).nameOptional)
                && roomNumberOptional.equals(((GuestPredicate) other).roomNumberOptional)
                && passportNumberOptional.equals(((GuestPredicate) other).passportNumberOptional))
                && tagsOptional.equals(((GuestPredicate) other).tagsOptional); // state check
    }


}
