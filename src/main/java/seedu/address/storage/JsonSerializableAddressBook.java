package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

//    private final List<JsonAdaptedGuest> guests = new ArrayList<>();
//    private final List<JsonAdaptedStaff> staffs = new ArrayList<>();
    // variable names here will become the key value pair for the json

    private final List<Map<String, List<? extends JsonAdaptedPerson>>> persons = new ArrayList<>();
    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     * The json property here reads from the addressbook.json for the header staff
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<Map<String, List<? extends JsonAdaptedPerson>>> persons) {
        @SuppressWarnings("unchecked")
        List<JsonAdaptedStaff> jsonStaff = (List<JsonAdaptedStaff>) persons.get(0).get("staff");
//        this.staffs.addAll(jsonStaff);
        @SuppressWarnings("unchecked")
        List<JsonAdaptedGuest> jsonGuest = (List<JsonAdaptedGuest>) persons.get(0).get("guest");
//        this.guests.addAll(jsonGuest);
        Map<String, List<? extends JsonAdaptedPerson>> jsonMap = new HashMap<>();
        jsonMap.put("staff", jsonStaff);
        jsonMap.put("guest", jsonGuest);
        this.persons.add(jsonMap);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     * This code saves the data in this format
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        List<JsonAdaptedGuest> guests = new ArrayList<>();
        List<JsonAdaptedStaff> staffs = new ArrayList<>();
        guests.addAll(source.getPersonList().stream().filter(x -> x.getTags().contains(new Tag("guest"))).map(JsonAdaptedGuest::new).collect(Collectors.toList()));
        staffs.addAll(source.getPersonList().stream().filter(x -> x.getTags().contains(new Tag("staff"))).map(JsonAdaptedStaff::new).collect(Collectors.toList()));
        Map<String, List<? extends JsonAdaptedPerson>> jsonMap = new HashMap<>();
        jsonMap.put("staff", staffs);
        jsonMap.put("guest", guests);
        persons.add(jsonMap);
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     * This code loads the data into the system.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        List<JsonAdaptedPerson> guests = (List<JsonAdaptedPerson>) persons.get(0).get("guest");
        List<JsonAdaptedPerson> staffs = (List<JsonAdaptedPerson>) persons.get(0).get("staff");
        // loop thru guest and add them into the address book
        for (JsonAdaptedPerson jsonAdaptedGuest : guests) {
            Person person = jsonAdaptedGuest.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        // loop thru staffs and add them into the address book
        for (JsonAdaptedPerson jsonAdaptedStaff : staffs) {
            Person person = jsonAdaptedStaff.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
