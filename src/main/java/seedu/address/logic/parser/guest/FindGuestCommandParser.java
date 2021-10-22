package seedu.address.logic.parser.guest;

public class FindGuestCommandParser {
}
//package seedu.address.logic.parser;
//
//import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
//import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;
//
//import java.util.Arrays;
//import java.util.stream.Stream;
//
//import seedu.address.logic.commands.ViewCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.IdentifierContainsKeywordsPredicate;
//
///**
// * Parses input arguments and creates a new FindCommand object
// */
//public class ViewCommandParser implements Parser<ViewCommand> {
//
//    /**
//     * Parses the given {@code String} of arguments in the context of the FindCommand
//     * and returns a FindCommand object for execution.
//     *
//     * @throws ParseException if the user input does not conform the expected format
//     */
//    public ViewCommand parse(String args) throws ParseException {
//        String trimmedArgs = args.trim();
//
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_MISSING_ARGUMENTS, ViewCommand.MESSAGE_USAGE));
//        }
//
//        String[] splitArguments = trimmedArgs.split(String.format("%s|%s", PREFIX_STAFF_ID, PREFIX_PASSPORT_NUMBER));
//
//        if (splitArguments.length > 2) { // Need to fix empty string inside String[]
//            throw new ParseException(
//                    String.format(MESSAGE_TOO_MANY_ARGUMENTS, ViewCommand.MESSAGE_USAGE));
//        }
//
//        ArgumentMultimap argMultimap =
//                ArgumentTokenizer.tokenize(args, PREFIX_STAFF_ID, PREFIX_PASSPORT_NUMBER);
//
//        if (arePrefixesPresent(argMultimap, PREFIX_STAFF_ID)) {
//            trimmedArgs = argMultimap.getValue(PREFIX_STAFF_ID).get();
//        }
//
//        if (arePrefixesPresent(argMultimap, PREFIX_PASSPORT_NUMBER)) {
//            trimmedArgs = argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get();
//        }
//
//        return new ViewCommand(new IdentifierContainsKeywordsPredicate(Arrays.asList(trimmedArgs)));
//    }
//
//    /**
//     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
//     * {@code ArgumentMultimap}.
//     */
//    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
//        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
//    }
//
//}
