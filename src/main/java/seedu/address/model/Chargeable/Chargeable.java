package seedu.address.model.Chargeable;

import seedu.address.model.commonattributes.Name;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.ServiceName;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


public class Chargeable {

    private final Name name;
    private final ServiceName serviceName;
    private final Cost cost;
    private Quantity quantity;

    public Chargeable(Name name, ServiceName serviceName, Cost cost, Quantity quantity) {
        requireAllNonNull(name, serviceName, cost);
        this.name = name;
        this.serviceName = serviceName;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Name getName() {
        return name;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public Cost getCost() {
        return cost;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity = new Quantity(quantity.value + 1);
    }

    /**
     * Returns true if both Chargeable have the same identity and data fields.
     * This defines a stronger notion of equality between two Chargeables.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Chargeable)) {
            return false;
        }

        Chargeable otherChargeable = (Chargeable) other;
        return otherChargeable.getName().equals(getName())
                && otherChargeable.getServiceName().equals(getServiceName())
                && otherChargeable.getCost().equals(getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, serviceName, cost);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company Name: ")
                .append(getName())
                .append("; Service provided: ")
                .append(getServiceName())
                .append("; Cost: ")
                .append(getCost())
                .append("; Quantity: ")
                .append(getQuantity());

        return builder.toString();
    }

}
