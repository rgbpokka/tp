package seedu.address.logic.commands.vendor;

public class FilterVendorCommandTest {
    
    //    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void equals() {
//        TagContainsKeywordsPredicate firstPredicate =
//                new TagContainsKeywordsPredicate(Collections.singletonList(new Tag("123")));
//        TagContainsKeywordsPredicate secondPredicate =
//                new TagContainsKeywordsPredicate(Collections.singletonList(new Tag("123456121D")));
//
//        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
//        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(filterFirstCommand.equals(filterFirstCommand));
//
//        // same values -> returns true
//        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
//        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(filterFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(filterFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(filterFirstCommand.equals(filterSecondCommand));
//    }
//
//    @Test
//    public void execute_noTags_noPersonFiltered() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        TagContainsKeywordsPredicate predicate = preparePredicate("  ");
//        FilterCommand command = new FilterCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        expectedModel.updateFilteredTagList(prepareTagPredicate(predicate));
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")).stream().map(Tag::new).collect(
//                Collectors.toList()));
//    }
//
//    private Predicate<Tag> prepareTagPredicate(TagContainsKeywordsPredicate predicate) {
//        return tag -> predicate.getTags().contains(tag);
//    }
    
    
}
