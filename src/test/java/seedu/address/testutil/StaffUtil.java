package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.guest.CheckInCommand;
import seedu.address.logic.commands.EditCommand.EditStaffDescriptor;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Staff.
 */
public class StaffUtil {

    /**
     * Returns an add command string for adding the {@code staff}.
     */
    public static String getAddCommand(Staff staff) {
        return CheckInCommand.COMMAND_WORD + " " + getStaffDetails(staff);
    }

    /**
     * Returns the part of command string for the given {@code staff}'s details.
     */
    public static String getStaffDetails(Staff staff) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + staff.getName().fullName + " ");
        sb.append(PREFIX_EMAIL + staff.getEmail().value + " ");
        sb.append(PREFIX_STAFF_ID + staff.getStaffId().value + " ");
        sb.append(PREFIX_PHONE + staff.getPhone().value + " ");
        sb.append(PREFIX_ADDRESS + staff.getAddress().value + " ");

        staff.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStaffDescriptor}'s details.
     */
    public static String getEditStaffDescriptorDetails(EditStaffDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getStaffId().ifPresent(staffId -> sb.append(PREFIX_STAFF_ID).append(staffId.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}

