package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.guest.GuestManager;
import seedu.address.model.guest.ReadOnlyGuestManager;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.guest.Guest;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.VendorManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final GuestManager guestManager;
    private final VendorManager vendorManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Guest> filteredGuests;
    private final FilteredList<Vendor> filteredVendors;

    /**
     * Initializes a ModelManager with the given guestManager, vendorManager and userPrefs.
     */
    public ModelManager(ReadOnlyGuestManager guestManager, ReadOnlyVendorManager vendorManager, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(guestManager, vendorManager, userPrefs);

        logger.fine("Initializing with Pocket Hotel: " + guestManager + " and user prefs " + userPrefs);

        this.guestManager = new GuestManager(guestManager);
        this.vendorManager = new VendorManager(vendorManager);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredGuests = new FilteredList<>(this.guestManager.getGuestList());
        filteredVendors = new FilteredList<>(this.vendorManager.getVendorList());
    }

    public ModelManager() {
        this(new GuestManager(), new VendorManager(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getGuestManagerFilePath() {
        return userPrefs.getGuestManagerFilePath();
    }

    @Override
    public void setGuestManagerFilePath(Path guestManagerFilePath) {
        requireNonNull(guestManagerFilePath);
        userPrefs.setGuestManagerFilePath(guestManagerFilePath);
    }

    //=========== Guest Manager ================================================================================

    @Override
    public void setGuestManager(ReadOnlyGuestManager guestManager) {
        this.guestManager.resetData(guestManager);
    }

    @Override
    public ReadOnlyGuestManager getGuestManager() {
        return guestManager;
    }

    @Override
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guestManager.hasGuest(guest);
    }

    @Override
    public void deleteGuest(Guest target) {
        guestManager.removeGuest(target);
    }

    @Override
    public void addGuest(Guest guest) {
        guestManager.addGuest(guest);
        updateFilteredGuestList(PREDICATE_SHOW_ALL_GUESTS);
    }

    @Override
    public void setGuest(Guest target, Guest editedGuest) {
        requireAllNonNull(target, editedGuest);
        guestManager.setGuest(target, editedGuest);
    }

    //=========== Vendor Manager ================================================================================
    @Override
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return vendorManager.hasVendor(vendor);
    }

    @Override
    public void deleteVendor(Vendor target) {
        vendorManager.removeVendor(target);
    }

    @Override
    public void addVendor(Vendor vendor) {
        vendorManager.addVendor(vendor);
        updateFilteredVendorList(PREDICATE_SHOW_ALL_VENDORS);
    }

    @Override
    public void setVendor(Vendor target, Vendor editedVendor) {
        requireAllNonNull(target, editedVendor);
        vendorManager.setVendor(target, editedVendor);
    }

    //=========== Filtered Guest/Vendor List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Guest} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Guest> getFilteredGuestList() {
        return filteredGuests;
    }

    @Override
    public ObservableList<Vendor> getFilteredVendorList() {
        return filteredVendors;
    }

    @Override
    public void updateFilteredGuestList(Predicate<Guest> predicate) {
        requireNonNull(predicate);
        filteredGuests.setPredicate(predicate);
    }

    @Override
    public void updateFilteredVendorList(Predicate<Vendor> predicate) {
        requireNonNull(predicate);
        filteredVendors.setPredicate(predicate);
    }

    @Override
    public void setVendorManager(ReadOnlyVendorManager vendorManager) {
        this.vendorManager.resetData(vendorManager);
    }

    @Override
    public ReadOnlyVendorManager getVendorManager() {
        return vendorManager;
    }

    @Override
    public Path getVendorManagerFilePath() {
        return userPrefs.getVendorManagerFilePath();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        return guestManager.equals(other.guestManager)
                && vendorManager.equals(other.vendorManager)
                && userPrefs.equals(other.userPrefs)
                && filteredGuests.equals(other.filteredGuests)
                && filteredVendors.equals(other.filteredVendors);
    }

}
