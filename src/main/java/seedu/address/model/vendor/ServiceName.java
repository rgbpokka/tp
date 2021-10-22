package seedu.address.model.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Vendor's service name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidServiceName(String)}
 */
public class ServiceName {

    public static final String MESSAGE_CONSTRAINTS =
            "Service names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the serviceName must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String serviceName;

    /**
     * Constructs a {@code ServiceName}.
     *
     * @param serviceName A valid serviceName.
     */
    public ServiceName(String serviceName) {
        requireNonNull(serviceName);
        checkArgument(isValidServiceName(serviceName), MESSAGE_CONSTRAINTS);
        this.serviceName = serviceName;
    }

    /**
     * Returns true if a given string is a valid serviceName.
     */
    public static boolean isValidServiceName(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    
    @Override
    public String toString() {
        return serviceName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ServiceName// instanceof handles nulls
                && serviceName.equals(((ServiceName) other).serviceName)); // state check
    }

    @Override
    public int hashCode() {
        return serviceName.hashCode();
    }

}
