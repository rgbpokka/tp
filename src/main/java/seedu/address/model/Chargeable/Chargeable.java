package seedu.address.model.Chargeable;

import seedu.address.model.commonattributes.Name;
import seedu.address.model.uniquelist.UniqueListItem;
import seedu.address.model.vendor.*;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


public class Chargeable extends UniqueListItem {

    private final Name name;
    private final ServiceName serviceName;
    private final Cost cost;

    public Chargeable(Name name, ServiceName serviceName, Cost cost) {
        requireAllNonNull(name, serviceName, cost);
        this.name = name;
        this.serviceName = serviceName;
        this.cost = cost;
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

    /**
     * Returns true if both Chargable have the same service name and cost.
     */
    @Override
    public boolean isSame(UniqueListItem otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (otherItem instanceof Chargeable) {
            Chargeable otherChargeable = (Chargeable) otherItem;
            return otherChargeable.getServiceName().equals(getServiceName()) && otherChargeable.getCost() == getCost();
        }

        return false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
        builder.append(getName())
                .append("; Cost: ")
                .append(getCost())
                .append("; Service provided: ")
                .append(getServiceName());

        return builder.toString();
    }

}
