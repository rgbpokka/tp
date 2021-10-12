package seedu.address.storage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
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

    private final List<Map<String, List<? extends Object>>> persons = new ArrayList<>();
    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     * The json property here reads from the addressbook.json for the header staff
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<Map<String, List<? extends Object>>> persons) {
        @SuppressWarnings("unchecked")
        List<JsonAdaptedStaff> jsonStaff = (List<JsonAdaptedStaff>) persons.get(0).get("staff");
//        this.staffs.addAll(jsonStaff);
        @SuppressWarnings("unchecked")
        List<JsonAdaptedGuest> jsonGuest = (List<JsonAdaptedGuest>) persons.get(0).get("guest");
//        this.guests.addAll(jsonGuest);
        Map<String, List<? extends Object>> jsonMap = new HashMap<>();
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
        guests.addAll(source.getPersonList().stream().filter(x -> x.getTags().contains(new Tag("Guest"))).map(JsonAdaptedGuest::new).collect(Collectors.toList()));
        staffs.addAll(source.getPersonList().stream().filter(x -> x.getTags().contains(new Tag("Staff"))).map(JsonAdaptedStaff::new).collect(Collectors.toList()));
        Map<String, List<? extends Object>> jsonMap = new HashMap<>();
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

        try{
            List<JsonAdaptedGuest> guests = jsonArrayToObjectList(toJsonString(persons.get(0).get("guest")), JsonAdaptedGuest.class);
            List<JsonAdaptedStaff> staffs = jsonArrayToObjectList(toJsonString(persons.get(0).get("staff")), JsonAdaptedStaff.class);
            // loop thru guest and add them into the address book
            for (JsonAdaptedGuest jsonAdaptedGuest : guests) {
                Person person = jsonAdaptedGuest.toModelType();
                if (addressBook.hasPerson(person)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
                }
                addressBook.addPerson(person);
            }

            for (JsonAdaptedStaff jsonAdaptedStaff : staffs) {
                Person person = jsonAdaptedStaff.toModelType();
                if (addressBook.hasPerson(person)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
                }
                addressBook.addPerson(person);
            }
            return addressBook;
        } catch (Exception e) {
            System.out.println("error");
        }

        return addressBook;
    }

    public <T> List<T> jsonArrayToObjectList(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
        List<T> ts = mapper.readValue(json, listType);
        System.out.println(("class name: {}"+ ts.get(0).getClass().getName()));
        return ts;
    }

    public static <T> String toJsonString(T instance) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
    }

}
