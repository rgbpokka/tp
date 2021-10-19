package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_ARGUMENTS, FilterCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.contains(PREFIX_TAG.getPrefix())) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String[] splitArguments = trimmedArgs.split(PREFIX_TAG.getPrefix());
        List<Tag> culledArguments =
                List.of(splitArguments).stream().filter(x -> !x.isEmpty()).map(String::trim).map(Tag::new).collect(
                        Collectors.toList());
        
        if (culledArguments.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_MISSING_ARGUMENTS, FilterCommand.MESSAGE_USAGE));
        }
        
        return new FilterCommand(new TagContainsKeywordsPredicate(culledArguments));
    }

}