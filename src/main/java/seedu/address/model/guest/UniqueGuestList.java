package seedu.address.model.guest;

import seedu.address.model.uniquelist.UniqueList;

import java.util.Optional;

public class UniqueGuestList extends UniqueList<Guest> {

    public Optional<Guest> get(PassportNumber passportNumber) {
        return super.asModifiableObservableList().stream().filter(
                guest -> guest.getPassportNumber().equals(passportNumber)).findFirst();
    }
}
