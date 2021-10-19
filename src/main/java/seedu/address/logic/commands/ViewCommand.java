package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;


import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.IdentifierContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the staff or guest by"
            + "the specified staff ID or passport number (case-insensitive) and displays them.\n"
            + "Parameters: " + PREFIX_PASSPORT_NUMBER + "PASSPORT NUMBER " + "or" + PREFIX_STAFF_ID + "STAFF ID" +
            "...\n"
            +"Example: " + COMMAND_WORD + PREFIX_STAFF_ID + " 101";

    private final IdentifierContainsKeywordsPredicate predicate;

    public ViewCommand(IdentifierContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
