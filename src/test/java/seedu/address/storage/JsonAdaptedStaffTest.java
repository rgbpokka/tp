package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StaffId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.JsonAdaptedStaff.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ELLE_STAFF;

class JsonAdaptedStaffTest {
    private static final String INVALID_SID = "asdas @";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_SID = ELLE_STAFF.getStaffId().toString();
    private static final String VALID_NAME = ELLE_STAFF.getName().toString();
    private static final String VALID_PHONE = ELLE_STAFF.getPhone().toString();
    private static final String VALID_EMAIL = ELLE_STAFF.getEmail().toString();
    private static final String VALID_ADDRESS = ELLE_STAFF.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = ELLE_STAFF.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStaff person = new JsonAdaptedStaff(ELLE_STAFF);
        assertEquals(ELLE_STAFF, person.toModelType());
    }

    @Test
    public void toModelType_invalidSId_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, INVALID_SID, VALID_PHONE);
        String expectedMessage = StaffId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSId_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, null, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StaffId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(INVALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_SID, VALID_PHONE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(null, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_SID, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_SID, INVALID_PHONE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_SID, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, INVALID_EMAIL, VALID_TAGS, VALID_ADDRESS, VALID_SID, VALID_PHONE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, null, VALID_TAGS, VALID_ADDRESS, VALID_SID, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_EMAIL, VALID_TAGS, INVALID_ADDRESS, VALID_SID, VALID_PHONE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_EMAIL, VALID_TAGS, null, VALID_SID, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStaff person =
                new JsonAdaptedStaff(VALID_NAME, VALID_EMAIL, invalidTags, VALID_ADDRESS, VALID_SID, VALID_PHONE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}