package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.PassportNumber;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.Staff;
import seedu.address.model.person.StaffId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Guest(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), getTagSet("friends", "Guest"),
                    new RoomNumber("123"), new PassportNumber("EC4744643")),
            new Guest(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                    getTagSet("colleagues", "friends", "Guest"),
                    new RoomNumber("456"), new PassportNumber("FG4741690")),
            new Staff(new Name("Charlotte Oliveiro"), new Email("charlotteo@example.com"),
                    getTagSet("neighbours", "Staff"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new StaffId("001"),
                    new Phone("93210283")
            )
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        Set<Tag> tagSet = new HashSet<>();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
            for (Tag tag : samplePerson.getTags()) {
                tagSet.add(tag);
            }
        }
        for (Tag sampleTag : tagSet) {
            sampleAb.addTag(sampleTag);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
