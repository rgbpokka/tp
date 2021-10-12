package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.PassportNumber;
import seedu.address.model.person.Person;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Guest}.
 */
class JsonAdaptedGuest{

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String roomNumber;
    private final String passportNumber;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGuest(@JsonProperty("name") String name,
                            @JsonProperty("email") String email,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("roomNumber") String roomNumber,
                            @JsonProperty("passportNumber") String passportNumber) {
        this.name = name;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.roomNumber = roomNumber;
        this.passportNumber = passportNumber;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedGuest(Person source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        Guest guest = (Guest) source;
        roomNumber = guest.getRoomNumber().value;
        passportNumber = guest.getPassportNumber().value;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<JsonAdaptedTag> getTags() {
        return tagged;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
//    @Override
    public Guest toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : getTags()) {
            personTags.add(tag.toModelType());
        }

        if (getName() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(getName())) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(getName());

        if (getEmail() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(getEmail())) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(getEmail());

        if (roomNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName()));
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        if (passportNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, PassportNumber.class.getSimpleName()));
        }
        if (!PassportNumber.isValidPassportNumber(passportNumber)) {
            throw new IllegalValueException(PassportNumber.MESSAGE_CONSTRAINTS);
        }
        final PassportNumber modelPassportNumber = new PassportNumber(passportNumber);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Guest(modelName, modelEmail, modelTags, modelRoomNumber, modelPassportNumber);
    }

}
