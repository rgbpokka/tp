package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSPORT_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFF_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DANIEL;
import static seedu.address.testutil.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.TypicalPassportNumbers.PASSPORT_NUMBER_THIRD_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_THIRD_PERSON;

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
            .withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTags("NORMALROOM", "OUTSTANDINGPAYMENT")
            .withRoomNumber("20201")
            .withPassportNumber(PASSPORT_NUMBER_SECOND_PERSON.toString())
            .build();

    public static final Guest CARL_GUEST = new GuestBuilder()
            .withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withRoomNumber("12321")
            .withPassportNumber(PASSPORT_NUMBER_THIRD_PERSON.toString())
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
            .withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withTags("MANAGER")
            .withAddress("michegan ave")
            .withPhone("9482224")
            .withStaffId(STAFF_ID_SECOND_PERSON.toString())
            .build();

    public static final Staff FIONA_STAFF = new StaffBuilder()
            .withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withPhone("9482427")
            .withStaffId(STAFF_ID_THIRD_PERSON.toString())
            .build();

    public static final Staff GEORGE_STAFF = new StaffBuilder()
            .withName("George Best")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withPhone("9482442")
            .withStaffId("101")
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
