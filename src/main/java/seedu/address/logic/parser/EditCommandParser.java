package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.address.logic.commands.EditCommand.EditStaffDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PassportNumber;
import seedu.address.model.person.StaffId;
import seedu.address.model.person.UniqueIdentifier;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_STAFF_ID, PREFIX_PASSPORT_NUMBER, PREFIX_ROOM_NUMBER);

        // will be assigned staff id or guest passport number depending on identity of person.
        UniqueIdentifier uniqueIdentifier;

        // determine if person is staff or guest based on whether staff id or passport number is present
        if (argMultimap.getValue(PREFIX_STAFF_ID).isPresent()) {
            StaffId staffId = ParserUtil.parseStaffId(argMultimap.getValue(PREFIX_STAFF_ID).get());
            EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptor();
            editStaffDescriptor.setStaffId(staffId);

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editStaffDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }

            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editStaffDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }

            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStaffDescriptor::setTags);

            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                editStaffDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
            }
            if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                editStaffDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
            }

            if (!editStaffDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }
            uniqueIdentifier = staffId;

            return new EditCommand(uniqueIdentifier, editStaffDescriptor);
        } else if (argMultimap.getValue(PREFIX_PASSPORT_NUMBER).isPresent()) {
            PassportNumber passportNumber =
                    ParserUtil.parsePassportNumber(argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get());
            EditGuestDescriptor editGuestDescriptor = new EditGuestDescriptor();
            editGuestDescriptor.setPassportNumber(passportNumber);

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                editGuestDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
            }

            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                editGuestDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
            }

            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editGuestDescriptor::setTags);

            if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
                editGuestDescriptor.setRoomNumber(
                        ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
            }

            if (!editGuestDescriptor.isAnyFieldEdited()) {
                throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
            }

            uniqueIdentifier = passportNumber;
            return new EditCommand(uniqueIdentifier, editGuestDescriptor);
        } else {
            throw new ParseException("Invalid unique identifier");
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
