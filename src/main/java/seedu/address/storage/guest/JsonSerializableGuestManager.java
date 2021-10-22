package seedu.address.storage.guest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestManager;
import seedu.address.model.guest.ReadOnlyGuestManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable GuestManager() that is serializable to JSON format.
 */
@JsonRootName(value = "guestManager")
public class JsonSerializableGuestManager {

    public static final String MESSAGE_DUPLICATE_GUEST = "Guest list contains duplicate guest(s).";

    private final List<JsonAdaptedGuest> guests = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGuestManager} with the given guests.
     * The json property here reads from the guests.json for the header staff
     */
    @JsonCreator
    public JsonSerializableGuestManager(@JsonProperty("guests") List<JsonAdaptedGuest> guests) {
        this.guests.addAll(guests);
    }

    /**
     * Converts a given {@code ReadOnlyGuestManager} into this class for Jackson use.
     * This code saves the data in this format
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGuestManager}.
     */
    public JsonSerializableGuestManager(ReadOnlyGuestManager source) {
        guests.addAll(source.getGuestList().stream().map(
                JsonAdaptedGuest::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code GuestManager} object.
     * This code loads the data into the system.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GuestManager toModelType() throws IllegalValueException {
        GuestManager guestManager = new GuestManager();
        for (JsonAdaptedGuest jsonAdaptedGuest : guests) {
            Guest guest = jsonAdaptedGuest.toModelType();
            if (guestManager.hasGuest(guest)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GUEST);
            }
            guestManager.addGuest(guest);
        }
        return guestManager;
    }

}
