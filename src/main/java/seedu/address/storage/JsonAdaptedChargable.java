package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Chargeable.Chargeable;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.vendor.*;

/**
 * Jackson-friendly version of {@link Vendor}.
 */
public class JsonAdaptedChargable {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Chargable's %s field is missing!";

    private final String name;
    private final String serviceName;
    private final Double cost;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedChargable(@JsonProperty("name") String name,
                             @JsonProperty("serviceName") String serviceName,
                             @JsonProperty("cost") Double cost) {
        this.name = name;
        this.serviceName = serviceName;
        this.cost = cost;
    }

    /**
     * Converts a given {@code Vendor} into this class for Jackson use.
     */
    public JsonAdaptedChargable(Chargeable source) {
        name = source.getName().fullName;
        serviceName = source.getServiceName().serviceName;
        cost = source.getCost().value;
    }

    public String getName() {
        return name;
    }

    /**
     * Converts this Jackson-friendly adapted vendor object into the model's {@code Vendor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    // @Override
    public Chargeable toModelType() throws IllegalValueException {

        if (getName() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(getName())) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(getName());

        if (!Cost.isValidCost(cost)) {
            throw new IllegalValueException(Cost.MESSAGE_CONSTRAINTS);
        }

        final Cost modelCost = new Cost(cost);

        if (!ServiceName.isValidServiceName(serviceName)) {
            throw new IllegalValueException(ServiceName.MESSAGE_CONSTRAINTS);
        }

        final ServiceName modelServiceName = new ServiceName(serviceName);


        return new Chargeable(modelName, modelServiceName, modelCost);
    }

}
