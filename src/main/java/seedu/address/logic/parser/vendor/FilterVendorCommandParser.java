package seedu.address.logic.parser.vendor;

import seedu.address.logic.commands.vendor.FilterVendorCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.ServiceName;
import seedu.address.model.vendor.VendorId;
import seedu.address.model.vendor.VendorPredicate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

/**
 * Parses input arguments and creates a new FilterVendorCommand object
 */
public class FilterVendorCommandParser implements Parser<FilterVendorCommand> {

    private static final String VALIDATION_COST_REGEX = "^[<,>]{0,1}[0-9]+(.[0-9]+)?$";
    

    /**
     * Parses the given {@code String} of arguments in the context of the FilterVendorCommand
     * and returns a FilterVendorCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterVendorCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG,
                        PREFIX_VENDOR_ID, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_SERVICE_NAME, PREFIX_COST,
                        PREFIX_OPERATING_HOURS);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_VENDOR_ID, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_SERVICE_NAME, PREFIX_COST,
                PREFIX_OPERATING_HOURS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterVendorCommand.MESSAGE_USAGE));
        }

        try {
            Optional<String> nameOptional = argMultimap.getValue(PREFIX_NAME);

            Optional<String> emailOptional = argMultimap.getValue(PREFIX_EMAIL);

            Optional<String> vendorId = argMultimap.getValue(PREFIX_VENDOR_ID);
            Optional<VendorId> vendorIdOptional =
                    vendorId.isEmpty()
                            ? Optional.empty()
                            : Optional.of(ParserUtil.parseVendorId(vendorId.get()));

            Optional<String> phoneOptional = argMultimap.getValue(PREFIX_PHONE);

            Optional<String> addressOptional = argMultimap.getValue(PREFIX_ADDRESS);

            Optional<String> serviceName = argMultimap.getValue(PREFIX_SERVICE_NAME);
            Optional<ServiceName> serviceNameOptional =
                    serviceName.isEmpty()
                            ? Optional.empty()
                            : Optional.of(ParserUtil.parseServiceName(serviceName.get()));

            Optional<String> costOptional = argMultimap.getValue(PREFIX_COST);
            if (!costOptional.get().matches(VALIDATION_COST_REGEX)) {
                throw new ParseException(Cost.MESSAGE_FILTER_CONSTRAINTS);
            }
            
            Optional<String> operatingHours = argMultimap.getValue(PREFIX_OPERATING_HOURS);
            Optional<OperatingHours> operatingHoursOptional =
                    operatingHours.isEmpty()
                            ? Optional.empty()
                            : Optional.of(ParserUtil.parseOperatingHours(operatingHours.get()));

            List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
            Optional<Set<Tag>> tagsOptional =
                    tags.isEmpty() ? Optional.empty() : Optional.of(ParserUtil.parseTags(tags));

            return new FilterVendorCommand(
                    new VendorPredicate(vendorIdOptional, phoneOptional, nameOptional, emailOptional,
                            tagsOptional, addressOptional, serviceNameOptional, costOptional, operatingHoursOptional));
        } catch (ParseException pe) {
            throw pe;
        }

    }

    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
