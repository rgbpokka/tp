package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.ReadOnlyGuestManager;
import seedu.address.storage.guest.GuestStorage;
import seedu.address.storage.vendor.VendorStorage;

/**
 * API of the Storage component
 */
public interface Storage extends GuestStorage, VendorStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getGuestManagerFilePath();

    @Override
    Optional<ReadOnlyGuestManager> readGuestManager() throws DataConversionException, IOException;

    @Override
    void saveGuestManager(ReadOnlyGuestManager guestManager) throws IOException;

}
