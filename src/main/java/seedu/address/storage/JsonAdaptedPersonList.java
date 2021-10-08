package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPersonList {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final List<JsonAdaptedStaff> staffs = new ArrayList<>();
    private final List<JsonAdaptedGuest> guests = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPersonList(@JsonProperty("staff") List<JsonAdaptedStaff> staffs, @JsonProperty("guest") List<JsonAdaptedGuest> guest) {

        if (staffs != null) {
            this.staffs.addAll(staffs);
        }
        if (guests != null) {
            this.guests.addAll(guests);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPersonList(Person source) {
//        name = source.getName().fullName;
//        phone = source.getPhone().value;
//        email = source.getEmail().value;
//        address = source.getAddress().value;
//        tagged.addAll(source.getTags().stream()
//                .map(JsonAdaptedTag::new)
//                .collect(Collectors.toList()));
    }

}
