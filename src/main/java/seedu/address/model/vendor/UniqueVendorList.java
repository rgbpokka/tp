package seedu.address.model.vendor;

import seedu.address.model.uniquelist.UniqueList;

import java.util.Optional;

public class UniqueVendorList extends UniqueList<Vendor> {
    
    public Optional<Vendor> get(VendorId vendorId) {
        return super.asModifiableObservableList().stream().filter(
                vendor -> vendor.getVendorId().equals(vendorId)).findFirst();
    }

}
