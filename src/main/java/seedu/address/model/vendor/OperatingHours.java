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

    public static final String VALIDATION_REGEX = "[1-7]{1,7}[\\s][0-2][0-9][0-5][0-9][-][0-2][0-9][0-5][0-9]";

    public final LocalTime startTime;
    public final LocalTime endTime;
    public final List<DayOfWeek> recurringDays;

    /**
     * @param startTime     The starting time.
     * @param endTime       The ending time.
     * @param recurringDays The days of the week it operates on.
     */
    public OperatingHours(LocalTime startTime, LocalTime endTime, List<DayOfWeek> recurringDays) {
        checkArgument(isValidTimings(startTime, endTime), MESSAGE_CONSTRAINTS);
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurringDays = recurringDays;
    }

    /**
     * Returns true is startTime is before endTime
     *
     * @param startTime The starting time.
     * @param endTime   The ending time.
     * @return
     */
    public static boolean isValidTimings(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    /**
     * Returns true if a given string are valid operating hours.
     */
    public static boolean isValidOperatingHours(String test) {
        System.out.println(test);
        return test.matches(VALIDATION_REGEX);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public List<DayOfWeek> getRecurringDays() {
        return recurringDays;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("Every ");
        for (DayOfWeek dayOfWeek : recurringDays) {
            resultString.append(dayOfWeek.toString() + ", ");
        }
        return resultString + " " + startTime.toString() + "-" + endTime.toString();
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
