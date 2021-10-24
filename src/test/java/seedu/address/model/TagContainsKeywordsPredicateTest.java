//package seedu.address.model;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALICE;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BENSON;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DANIEL;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ELLE;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.tag.Tag;
//import seedu.address.model.tag.TagContainsKeywordsPredicate;
//import seedu.address.testutil.guest.GuestBuilder;
//import seedu.address.testutil.vendor.VendorBuilder;
//
//public class TagContainsKeywordsPredicateTest {
//
//    @Test
//    public void equals() {
//        List<Tag> firstPredicateKeywordList = Collections.singletonList(new Tag("first"));
//        List<Tag> secondPredicateKeywordList = Arrays.asList(new Tag("first"), new Tag("second"));
//
//        TagContainsKeywordsPredicate firstPredicate =
//                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
//        TagContainsKeywordsPredicate secondPredicate =
//                new TagContainsKeywordsPredicate(secondPredicateKeywordList);
//
//        // same object -> returns true
//        assertTrue(firstPredicate.equals(firstPredicate));
//
//        // same values -> returns true
//        TagContainsKeywordsPredicate firstPredicateCopy =
//                new TagContainsKeywordsPredicate(firstPredicateKeywordList);
//        assertTrue(firstPredicate.equals(firstPredicateCopy));
//
//        // different types -> returns false
//        assertFalse(firstPredicate.equals(1));
//
//        // null -> returns false
//        assertFalse(firstPredicate.equals(null));
//
//        // different person -> returns false
//        assertFalse(firstPredicate.equals(secondPredicate));
//    }
//
//    @Test
//    public void test_tagsContainsKeywords_returnsTrue() {
//        // Test for Guest
//        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
//                Collections.singletonList(new Tag(VALID_TAG_ALICE)));
//        assertTrue(predicate.test(
//                new GuestBuilder().withTags(VALID_TAG_ALICE).build()));
//
//        predicate = new TagContainsKeywordsPredicate(
//                Collections.singletonList(new Tag(VALID_TAG_BENSON)));
//        assertTrue(predicate.test(
//                new GuestBuilder().withTags(VALID_TAG_BENSON).build()));
//
//        // Test for Staff
//        predicate = new TagContainsKeywordsPredicate(
//                Collections.singletonList(new Tag(VALID_TAG_DANIEL)));
//        assertTrue(predicate.test(
//                new VendorBuilder().withTags(VALID_TAG_DANIEL).build()));
//
//        predicate = new TagContainsKeywordsPredicate(
//                Collections.singletonList(new Tag(VALID_TAG_ELLE)));
//        assertTrue(predicate.test(
//                new VendorBuilder().withTags(VALID_TAG_ELLE).build()));
//    }
//
//    @Test
//    public void test_tagsDoesNotContainKeywords_returnsFalse() {
//        // Zero keywords (Empty tag t/)
//        TagContainsKeywordsPredicate predicate =
//                new TagContainsKeywordsPredicate(Collections.emptyList());
//        assertFalse(predicate.test(new VendorBuilder().withTags(VALID_TAG_DANIEL).build()));
//
//        predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
//        assertFalse(predicate.test(
//                new GuestBuilder().withTags(VALID_TAG_ALICE).build()));
//
//        // Non-matching keyword
//        predicate =
//                new TagContainsKeywordsPredicate(Arrays.asList(new Tag(VALID_TAG_DANIEL)));
//        assertFalse(predicate.test(new VendorBuilder().withTags(VALID_TAG_ELLE).build()));
//
//        predicate = new TagContainsKeywordsPredicate(
//                Collections.singletonList(new Tag(VALID_TAG_ALICE)));
//        assertFalse(predicate.test(new GuestBuilder().withTags(
//                VALID_TAG_BENSON).build()));
//    }
//
//}
