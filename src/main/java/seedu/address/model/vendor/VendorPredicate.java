package seedu.address.model.vendor;

import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Tests that a {@code Vendor} matches the {@code VendorId}, {@code Phone}, {@code Name}, {@code Email},
 * {@code Address}, {@code ServiceName}, {@code Cost}, {@code OperatingHours}, and list of {@code Tag} given.
 */
public class VendorPredicate implements Predicate<Vendor> {

    private final Optional<VendorId> vendorIdOptional;
    private final Optional<Phone> phoneOptional;
    private final Optional<Name> nameOptional;
    private final Optional<Email> emailOptional;
    private final Optional<Set<Tag>> tagsOptional;
    private final Optional<Address> addressOptional;
    private final Optional<ServiceName> serviceNameOptional;
    private final Optional<Cost> costOptional;
    private final Optional<OperatingHours> operatingHoursOptional;

    public VendorPredicate(Optional<VendorId> vendorIdOptional,
                           Optional<Phone> phoneOptional,
                           Optional<Name> nameOptional,
                           Optional<Email> emailOptional,
                           Optional<Set<Tag>> tagsOptional,
                           Optional<Address> addressOptional,
                           Optional<ServiceName> serviceNameOptional,
                           Optional<Cost> costOptional,
                           Optional<OperatingHours> operatingHoursOptional) {
        requireAllNonNull(vendorIdOptional, phoneOptional, nameOptional, emailOptional, tagsOptional, addressOptional,
                serviceNameOptional, costOptional, operatingHoursOptional);
        if (tagsOptional.isPresent()) {
            this.tagsOptional = Optional.of(new HashSet<>(tagsOptional.get()));
        } else {
            this.tagsOptional = Optional.empty();
        }
        this.vendorIdOptional = vendorIdOptional;
        this.phoneOptional = phoneOptional;
        this.nameOptional = nameOptional;
        this.emailOptional = emailOptional;
        this.addressOptional = addressOptional;
        this.serviceNameOptional = serviceNameOptional;
        this.costOptional = costOptional;
        this.operatingHoursOptional = operatingHoursOptional;
    }

    @Override
    public boolean test(Vendor vendor) {
        requireNonNull(vendor);
        return testForEmail(vendor) && testForName(vendor) && testForTags(vendor) && testForPhone(vendor) &&
                testForVendorId(vendor) && testForAddress(vendor) && testForCost(vendor) &&
                testForServiceName(vendor) && testForOperatingHours(vendor);
    }

    private boolean testForVendorId(Vendor vendor) {
        if (vendorIdOptional.isPresent()) {
            return vendorIdOptional.get().equals(vendor.getVendorId());
        }
        return true;
    }

    private boolean testForName(Vendor vendor) {
        if (nameOptional.isPresent()) {
            return nameOptional.get().equals(vendor.getName());
        }
        return true;
    }

    private boolean testForEmail(Vendor vendor) {
        if (emailOptional.isPresent()) {
            return emailOptional.get().equals(vendor.getEmail());
        }
        return true;
    }

    private boolean testForPhone(Vendor vendor) {
        if (phoneOptional.isPresent()) {
            return phoneOptional.get().equals(vendor.getPhone());
        }
        return true;
    }

    private boolean testForAddress(Vendor vendor) {
        if (addressOptional.isPresent()) {
            return addressOptional.get().equals(vendor.getAddress());
        }
        return true;
    }

    private boolean testForServiceName(Vendor vendor) {
        if (serviceNameOptional.isPresent()) {
            return serviceNameOptional.get().equals(vendor.getServiceName());
        }
        return true;
    }

    private boolean testForCost(Vendor vendor) {
        if (costOptional.isPresent()) {
            return costOptional.get().equals(vendor.getCost());
        }
        return true;
    }

    private boolean testForOperatingHours(Vendor vendor) {
        if (operatingHoursOptional.isPresent()) {
            return operatingHoursOptional.get().equals(vendor.getOperatingHours());
        }
        return true;
    }

    private boolean testForTags(Vendor vendor) {
        if (tagsOptional.isPresent()) {
            return tagsOptional.get().stream()
                    .anyMatch(tag -> vendor.getTags().contains(tag));
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VendorPredicate // instanceof handles nulls
                && emailOptional.equals(((VendorPredicate) other).emailOptional)
                && nameOptional.equals(((VendorPredicate) other).nameOptional)
                && phoneOptional.equals(((VendorPredicate) other).phoneOptional)
                && vendorIdOptional.equals(((VendorPredicate) other).vendorIdOptional)
                && addressOptional.equals(((VendorPredicate) other).addressOptional)
                && serviceNameOptional.equals(((VendorPredicate) other).serviceNameOptional)
                && costOptional.equals(((VendorPredicate) other).costOptional)
                && operatingHoursOptional.equals(((VendorPredicate) other).operatingHoursOptional)
                && tagsOptional.equals(((VendorPredicate) other).tagsOptional)); // state check
    }


}
