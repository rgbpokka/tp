package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_GEORGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CARL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {
    public static final Guest ALICE_GUEST = new GuestBuilder()
            .withName(VALID_NAME_ALICE)
            .withEmail(VALID_EMAIL_ALICE)
            .withTags(VALID_TAG_ALICE)
            .withRoomNumber(VALID_ROOM_NUMBER_ALICE)
            .withPassportNumber(VALID_PASSPORT_NUMBER_ALICE)
            .build();

    public static final Guest BENSON_GUEST = new GuestBuilder()
            .withName(VALID_NAME_BENSON)
            .withEmail(VALID_EMAIL_BENSON)
            .withTags(VALID_TAG_BENSON)
            .withRoomNumber(VALID_ROOM_NUMBER_BENSON)
            .withPassportNumber(VALID_PASSPORT_NUMBER_BENSON)
            .build();

    public static final Guest CARL_GUEST = new GuestBuilder()
            .withName(VALID_NAME_CARL)
            .withEmail(VALID_EMAIL_CARL)
            .withTags(VALID_TAG_CARL)
            .withRoomNumber(VALID_ROOM_NUMBER_CARL)
            .withPassportNumber(VALID_PASSPORT_NUMBER_CARL)
            .build();

    public static final Staff DANIEL_STAFF = new StaffBuilder()
            .withName(VALID_NAME_DANIEL)
            .withEmail(VALID_EMAIL_DANIEL)
            .withTags(VALID_TAG_DANIEL)
            .withAddress(VALID_ADDRESS_DANIEL)
            .withPhone(VALID_PHONE_DANIEL)
            .withStaffId(VALID_STAFF_ID_DANIEL)
            .build();

    public static final Staff ELLE_STAFF = new StaffBuilder()
            .withName(VALID_NAME_ELLE)
            .withEmail(VALID_EMAIL_ELLE)
            .withTags(VALID_TAG_ELLE)
            .withAddress(VALID_ADDRESS_ELLE)
            .withPhone(VALID_PHONE_ELLE)
            .withStaffId(VALID_STAFF_ID_ELLE)
            .build();

    public static final Staff FIONA_STAFF = new StaffBuilder()
            .withName(VALID_NAME_FIONA)
            .withEmail(VALID_EMAIL_FIONA)
            .withTags(VALID_TAG_FIONA)
            .withAddress(VALID_ADDRESS_FIONA)
            .withPhone(VALID_PHONE_FIONA)
            .withStaffId(VALID_STAFF_ID_FIONA)
            .build();

    public static final Staff GEORGE_STAFF = new StaffBuilder()
            .withName(VALID_NAME_GEORGE)
            .withEmail(VALID_EMAIL_GEORGE)
            .withTags(VALID_TAG_GEORGE)
            .withAddress(VALID_ADDRESS_GEORGE)
            .withPhone(VALID_PHONE_GEORGE)
            .withStaffId(VALID_STAFF_ID_GEORGE)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        Set<Tag> tagSet = new HashSet<>();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
            for (Tag tag : person.getTags()) {
                tagSet.add(tag);
            }
        }

        for (Tag typicalTag : tagSet) {
            ab.addTag(typicalTag);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(
                Arrays.asList(
                        ALICE_GUEST,
                        BENSON_GUEST,
                        CARL_GUEST,
                        DANIEL_STAFF,
                        ELLE_STAFF,
                        FIONA_STAFF,
                        GEORGE_STAFF)
        );
    }
}
