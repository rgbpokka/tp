package seedu.address.model.vendor;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class OperatingHours {

    public static final String MESSAGE_CONSTRAINTS =
            "Operating Hours must only have valid days of the week (1 - 7), and the end time and " +
                    "start must be between 0000 and 2359, and start time less than end time.";

    public final LocalTime startTime;
    public final LocalTime endTime;
    public final List<DayOfWeek> recurringDays;
    
    /**
     * 
     * @param startTime The starting time.
     * @param endTime The ending time.
     * @param recurringDays The days of the week it operates on.
     */
    public OperatingHours(LocalTime startTime, LocalTime endTime, List<DayOfWeek> recurringDays) {
        checkArgument(isValidOperatingHours(startTime, endTime), MESSAGE_CONSTRAINTS);
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurringDays = recurringDays;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidOperatingHours(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    @Override
    public String toString() {
        String resultString = "Recurring Days: ";
        for (DayOfWeek dayOfWeek : recurringDays) {
            resultString += dayOfWeek.toString();
        }
        return resultString + "\n" + startTime.toString() + "\n" + endTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OperatingHours // instanceof handles nulls
                && startTime.equals(((OperatingHours) other).startTime))
                && endTime.equals(((OperatingHours) other).endTime)
                && recurringDays.equals(((OperatingHours) other).recurringDays); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, recurringDays);
    }

}
