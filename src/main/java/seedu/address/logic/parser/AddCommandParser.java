package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Staff;
import seedu.address.model.person.StaffId;
import seedu.address.model.person.PassportNumber;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Email;
import seedu.address.model.person.Address;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (arePrefixesPresent(argMultimap, PREFIX_PASSPORT, PREFIX_STAFF) || !argMultimap.getPreamble().isEmpty()) {
            //think of new error msg for only can have either passport number or staffid
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_PASSPORT) && !arePrefixesPresent(argMultimap, PREFIX_STAFF)) {
            // new error msg for needing passport number OR staff id
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_PASSPORT)) {
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PASSPORT, PREFIX_ROOM, PREFIX_EMAIL)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            RoomNumber roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM).get());
            PassportNumber passport = ParserUtil.parsePassportNumber(argMultimap.getValue(PREFIX_PASSPORT).get());

            Guest guest = new Guest(name, email, tagList, roomNumber, passport);
            return new AddCommand(guest);
        }

        if (arePrefixesPresent(argMultimap, PREFIX_STAFF)) {
            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_STAFF, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            StaffId staffId = ParserUtil.parseStaffId(argMultimap.getValue(PREFIX_STAFF).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());

            Staff staff = new Staff(name, email, tagList, address, staffId, phone);
            return new AddCommand(staff);
        }

//
//        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
//        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
//        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
//        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
//        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
//
//        Person person = new Person(name, phone, email, address, tagList);
//
//        return new AddCommand(person);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        StaffId staffId = ParserUtil.parseStaffId(argMultimap.getValue(PREFIX_STAFF).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());

        Staff staff = new Staff(name, email, tagList, address, staffId, phone);
        return new AddCommand(staff);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
