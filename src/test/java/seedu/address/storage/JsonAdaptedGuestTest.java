package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON_GUEST;

class JsonAdaptedGuestTest {
    private static final String INVALID_PASSPORTNUMBER = "@@@@@";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ROOMNUMBER = "@@@@@@";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_PASSPORTNUMBER = BENSON_GUEST.getPassportNumber().toString();
    private static final String VALID_NAME = BENSON_GUEST.getName().toString();
    private static final String VALID_ROOMNUMBER = BENSON_GUEST.getRoomNumber().toString();
    private static final String VALID_EMAIL = BENSON_GUEST.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON_GUEST.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedGuest person = new JsonAdaptedGuest(BENSON_GUEST);
        assertEquals(BENSON_GUEST, person.toModelType());
    }

    @Test
    public void toModelType_invalidPassportNumber_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ROOMNUMBER, INVALID_PASSPORTNUMBER);
        String expectedMessage = PassportNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPassportNumber_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ROOMNUMBER, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PassportNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(INVALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ROOMNUMBER, VALID_PASSPORTNUMBER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(null, VALID_EMAIL, VALID_TAGS, VALID_ROOMNUMBER, VALID_PASSPORTNUMBER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRoomNumber_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, INVALID_ROOMNUMBER, VALID_PASSPORTNUMBER);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRoomNumber_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, null, VALID_PASSPORTNUMBER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(VALID_NAME, INVALID_EMAIL, VALID_TAGS, VALID_ROOMNUMBER, VALID_PASSPORTNUMBER);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedGuest person = new JsonAdaptedGuest(VALID_NAME, null, VALID_TAGS, VALID_ROOMNUMBER, VALID_PASSPORTNUMBER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedGuest person =
                new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, invalidTags, VALID_ROOMNUMBER, VALID_PASSPORTNUMBER);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}