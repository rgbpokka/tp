//package seedu.address.logic.commands;
//
//import static java.util.Objects.requireNonNull;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
//
//import java.util.function.Predicate;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.model.Model;
//import seedu.address.model.tag.TagContainsKeywordsPredicate;
//import seedu.address.model.tag.Tag;
//
///**
// * Finds and lists all persons in address book whose name contains any of the argument keywords.
// * Keyword matching is case insensitive.
// */
//public class FilterCommand extends Command {
//
//    public static final String COMMAND_WORD = "filter";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the list by tags: "
//            + PREFIX_TAG + "TAG" + "...\n"
//            + "Example: " + COMMAND_WORD + " "
//            + PREFIX_TAG + "Staff "
//            + PREFIX_TAG + "Vaccinated "
//            + "\n This filters the list by those that are vaccinated and are Staff.\n";
//
//    private final TagContainsKeywordsPredicate predicate;
//
//    public FilterCommand(TagContainsKeywordsPredicate predicate) {
//        this.predicate = predicate;
//    }
//
//    @Override
//    public CommandResult execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredPersonList(predicate);
//        model.updateFilteredTagList(getTagPredicate(predicate));
//        return new CommandResult(
//                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
//    }
//
//    private Predicate<Tag> getTagPredicate(TagContainsKeywordsPredicate predicate) {
//        return tag -> predicate.getTags().contains(tag);
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof FilterCommand // instanceof handles nulls
//                && predicate.equals(((FilterCommand) other).predicate)); // state check
//    }
//
//}
