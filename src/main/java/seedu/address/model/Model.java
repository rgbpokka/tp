package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.guest.ReadOnlyGuestManager;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.guest.Guest;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Guest> PREDICATE_SHOW_ALL_GUESTS = unused -> true;
    Predicate<Vendor> PREDICATE_SHOW_ALL_VENDORS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' guest manager file path.
     */
    Path getGuestManagerFilePath();

    /**
     * Sets the user prefs' guest manager file path.
     */
    void setGuestManagerFilePath(Path guestManagerFilePath);

    /**
     * Replaces pocket hotel data with the data in {@code guestManager}.
     */
    void setGuestManager(ReadOnlyGuestManager guestManager);

    /**
     * Returns the GuestManager
     */
    ReadOnlyGuestManager getGuestManager();
    
    // ==================== Guest operations =====================    
    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the address book.
     */
    boolean hasGuest(Guest guest);

    /**
     * Deletes the given guest.
     * The guest must exist in the address book.
     */
    void deleteGuest(Guest target);

    /**
     * Adds the given guest.
     * {@code guest} must not already exist in the address book.
     */
    void addGuest(Guest guest);

    /**
     * Replaces the given guest {@code target} with {@code editedGuest}.
     * {@code target} must exist in the address book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the address book.
     */
    void setGuest(Guest target, Guest editedGuest);

    /**
     * Returns an unmodifiable view of the filtered guest list
     */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Updates the filter of the filtered guest list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGuestList(Predicate<Guest> predicate);
 
    // ==================== Vendor operations =====================
    /**
     * Returns true if a tag with the same identity as {@code vendor} exists in the address book.
     */
    boolean hasVendor(Vendor vendor);

    /**
     * Deletes the given vendor.
     * The vendor must exist in the address book.
     */
    void deleteVendor(Vendor target);

    /**
     * Adds the given vendor.
     * {@code vendor} must not already exist in the address book.
     */
    void addVendor(Vendor vendor);

    /**
     * Replaces the given vendor {@code target} with {@code editedVendor}.
     * {@code target} must exist in the address book.
     * The vendor identity of {@code editedVendor} must not be the same as another existing vendor in the address book.
     */
    void setVendor(Vendor target, Vendor editedVendor);

    /**
     * Returns an unmodifiable view of the filtered vendor list
     */
    ObservableList<Vendor> getFilteredVendorList();

    /**
     * Updates the filter of the filtered vendor list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVendorList(Predicate<Vendor> predicate);

    /**
     * Replaces pocket hotel data with the data in {@code vendorManager}.
     */
    void setVendorManager(ReadOnlyVendorManager vendorManager);

    /**
     * Returns the VendorManager
     */
    ReadOnlyVendorManager getVendorManager();

    Path getVendorManagerFilePath();
}
