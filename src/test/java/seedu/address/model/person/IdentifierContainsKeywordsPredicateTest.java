package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GuestBuilder;
import seedu.address.testutil.StaffBuilder;

public class IdentifierContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        IdentifierContainsKeywordsPredicate firstPredicate = new IdentifierContainsKeywordsPredicate(firstPredicateKeywordList);
        IdentifierContainsKeywordsPredicate secondPredicate = new IdentifierContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdentifierContainsKeywordsPredicate firstPredicateCopy = new IdentifierContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_identifierContainsKeywords_returnsTrue() {
        // Test for Guest 
        IdentifierContainsKeywordsPredicate predicate = new IdentifierContainsKeywordsPredicate(Collections.singletonList("T1231231D"));
        assertTrue(predicate.test(new GuestBuilder().withPassportNumber("T1231231D").build()));

        predicate = new IdentifierContainsKeywordsPredicate(Collections.singletonList("t1231231D"));
        assertTrue(predicate.test(new GuestBuilder().withPassportNumber("T1231231D").build()));

        // Test for Staff
        predicate = new IdentifierContainsKeywordsPredicate(Collections.singletonList("123"));
        assertTrue(predicate.test(new StaffBuilder().withStaffId("123").build()));

        predicate = new IdentifierContainsKeywordsPredicate(Collections.singletonList("s123"));
        assertTrue(predicate.test(new StaffBuilder().withStaffId("S123").build())); 

    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IdentifierContainsKeywordsPredicate predicate = new IdentifierContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StaffBuilder().withStaffId("123").build()));

        predicate = new IdentifierContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new GuestBuilder().withPassportNumber("T1231231D").build())); 

        // Non-matching keyword
        predicate = new IdentifierContainsKeywordsPredicate(Arrays.asList("001"));
        assertFalse(predicate.test(new StaffBuilder().withStaffId("002").build()));

        predicate = new IdentifierContainsKeywordsPredicate(Collections.singletonList("S32131231E"));
        assertFalse(predicate.test(new GuestBuilder().withPassportNumber("T1231231D").build()));

        // Keywords match name, email and other respective fields, but does not match identifier 
        predicate = new IdentifierContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new StaffBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withStaffId("123").build()));

        predicate = new IdentifierContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com"));
        assertFalse(predicate.test(new GuestBuilder().withName("Alice").withRoomNumber("12345")
                .withEmail("alice@email.com").withPassportNumber("T12312123E").build())); 
        
    }
}
