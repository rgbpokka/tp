package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StaffId extends UniqueIdentifier {

    public static final String MESSAGE_CONSTRAINTS = "StaffID must be alphanumeric.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code StaffId}.
     *
     * @param staffId A valid staffId 
     */
    public StaffId(String staffId) {
        requireNonNull(staffId);
        checkArgument(isValidStaffId(staffId), MESSAGE_CONSTRAINTS);
        this.value = staffId;
    }

    /**
     * Returns true if a given string is a valid staff ID.  
     */
    public static boolean isValidStaffId(String staffId) {
        return staffId.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffId // instanceof handles nulls
                && value.equals(((StaffId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    
}
