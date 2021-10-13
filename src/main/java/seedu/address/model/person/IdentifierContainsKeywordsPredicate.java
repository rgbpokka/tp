package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Identifier} matches any of the keywords given.
 */
public class IdentifierContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public IdentifierContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (person instanceof Staff) {
            Staff staff = (Staff) person;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(staff.getStaffId().value, keyword));
        } else {
            Guest guest = (Guest) person;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(guest.getPassportNumber().value, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdentifierContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((IdentifierContainsKeywordsPredicate) other).keywords)); // state check
    }

}
