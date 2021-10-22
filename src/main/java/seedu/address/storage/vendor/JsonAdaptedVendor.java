package seedu.address.storage.vendor;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.vendor.Address;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.Phone;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorId;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAdaptedTag;

/**
 * Jackson-friendly version of {@link Vendor}.
 */
class JsonAdaptedVendor {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Vendor's %s field is missing!";

    private final String name;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String phone;
    private final String address;
    private final String vendorId;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedVendor(@JsonProperty("name") String name,
                             @JsonProperty("email") String email,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("address") String address,
                             @JsonProperty("vendorId") String vendorId,
                             @JsonProperty("phone") String phone) {
        this.name = name;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.address = address;
        this.vendorId = vendorId;
        this.phone = phone;
    }

    /**
     * Converts a given {@code Vendor} into this class for Jackson use.
     */
    public JsonAdaptedVendor(Vendor source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        phone = source.getPhone().value;
        address = source.getAddress().value;
        vendorId = source.getVendorId().value;
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
     * Converts this Jackson-friendly adapted vendor object into the model's {@code Vendor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    // @Override
    public Vendor toModelType() throws IllegalValueException {
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

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (vendorId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, VendorId.class.getSimpleName()));
        }
        if (!VendorId.isValidVendorId(vendorId)) {
            throw new IllegalValueException(VendorId.MESSAGE_CONSTRAINTS);
        }
        final VendorId modelVendorId = new VendorId(vendorId);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Cost modelCost = new Cost(1.2);

        final OperatingHours modelOperatingHours =
                new OperatingHours(LocalTime.of(10, 0), LocalTime.of(15, 0), new ArrayList<DayOfWeek>(
                        Collections.singleton(DayOfWeek.of(1))));

        final ServiceName modelServiceName = new ServiceName("massage");

        return new Vendor(modelName, modelEmail, modelTags, modelVendorId, modelPhone, modelServiceName, modelAddress,
                modelCost, modelOperatingHours);
    }

}
