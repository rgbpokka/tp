package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.ReadOnlyGuestManager;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the GuestManager.
     *
     * @see seedu.address.model.Model#getGuestManager()
     */
    ReadOnlyGuestManager getGuestManager();

    /**
     * Returns an unmodifiable view of the filtered list of guests 
     */
    ObservableList<Guest> getFilteredGuestList();

    /**
     * Returns the VendorManager.
     *
     * @see seedu.address.model.Model#getVendorManager()
     */
    ReadOnlyVendorManager getVendorManager();
    
    /**
     * Returns an unmodifiable view of the filtered list of vendors 
     */
    ObservableList<Vendor> getFilteredVendorList();

    /**
     * Returns the user prefs' guest manager file path.
     */
    Path getGuestManagerFilePath();

    /**
     * Returns the user prefs' vendor manager file path.
     */
    Path getVendorManagerFilePath(); 

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
