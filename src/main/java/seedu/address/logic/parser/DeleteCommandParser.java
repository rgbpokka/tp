package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MULTIPLE_UNIQUE_IDENTIFIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.UniqueIdentifier;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF_ID, PREFIX_PASSPORT_NUMBER);

        UniqueIdentifier uniqueIdentifier;
        try {
            // Identify staff or guest
            if (argMultimap.getValue(PREFIX_STAFF_ID).isPresent()
                    && argMultimap.getValue(PREFIX_PASSPORT_NUMBER).isPresent()) {
                throw new ParseException(MESSAGE_INVALID_MULTIPLE_UNIQUE_IDENTIFIER);
            } else if (argMultimap.getValue(PREFIX_STAFF_ID).isPresent()) {
                uniqueIdentifier = ParserUtil.parseStaffId(argMultimap.getValue(PREFIX_STAFF_ID).get());
            } else if (argMultimap.getValue(PREFIX_PASSPORT_NUMBER).isPresent()) {
                uniqueIdentifier = ParserUtil.parsePassportNumber(
                        argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get());
            } else {
                // Message left blank as the catch statements message is already sufficient
                throw new ParseException("");
            }
            return new DeleteCommand(uniqueIdentifier);

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}

