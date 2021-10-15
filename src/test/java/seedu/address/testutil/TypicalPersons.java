package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

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
    // Guests

    public static final Guest ALICE_GUEST = new GuestBuilder()
            .withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withTags("VIP")
            .withRoomNumber("20202")
            .withPassportNumber("T01919191")
            .build();

    public static final Guest BENSON_GUEST = new GuestBuilder()
            .withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTags("NORMAL ROOM", "OUTSTANDING PAYMENT")
            .withRoomNumber("20201")
            .withPassportNumber("T01919190")
            .build();

    public static final Guest CARL_GUEST = new GuestBuilder()
            .withName("Carl Kurz")
            .withEmail("heinz@example.com")
            .withRoomNumber("12321")
            .withPassportNumber("T01988190")
            .build();

    public static final Staff DANIEL_STAFF = new StaffBuilder()
            .withName("Daniel Meier")
            .withEmail("cornelia@example.com")
            .withTags("COUNTER STAFF")
            .withAddress("10th street")
            .withPhone("87652533")
            .withStaffId("345")
            .build();

    public static final Staff ELLE_STAFF = new StaffBuilder()
            .withName("Elle Meyer")
            .withEmail("werner@example.com")
            .withTags("MANAGER")
            .withAddress("michegan ave")
            .withPhone("9482224")
            .withStaffId("678")
            .build();

    public static final Staff FIONA_STAFF = new StaffBuilder()
            .withName("Fiona Kunz")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withPhone("9482427")
            .withStaffId("901")
            .build();

    public static final Staff GEORGE_STAFF = new StaffBuilder()
            .withName("George Best")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withPhone("9482442")
            .withStaffId("101")
            .build();

    // Manually added
//    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
//            .withEmail("stefan@example.com").withAddress("little india").build();
//    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
//            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Staff AMY = new StaffBuilder().withStaffId("45").withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Staff BOB = new StaffBuilder().withStaffId("46").withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

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
