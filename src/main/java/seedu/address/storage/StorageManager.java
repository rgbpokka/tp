package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.ReadOnlyGuestManager;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.storage.guest.GuestStorage;
import seedu.address.storage.vendor.VendorStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private VendorStorage vendorStorage;
    private GuestStorage guestStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(GuestStorage guestStorage, VendorStorage vendorStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.guestStorage = guestStorage;
        this.vendorStorage = vendorStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ GuestManager methods ==============================

    @Override
    public Path getGuestManagerFilePath() {
        return guestStorage.getGuestManagerFilePath();
    }

    @Override
    public Optional<ReadOnlyGuestManager> readGuestManager() throws DataConversionException, IOException {
        return readGuestManager(guestStorage.getGuestManagerFilePath());
    }

    @Override
    public Optional<ReadOnlyGuestManager> readGuestManager(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return guestStorage.readGuestManager(filePath);
    }

    @Override
    public void saveGuestManager(ReadOnlyGuestManager guestManager) throws IOException {
        saveGuestManager(guestManager, guestStorage.getGuestManagerFilePath());
    }

    @Override
    public void saveGuestManager(ReadOnlyGuestManager guestManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        guestStorage.saveGuestManager(guestManager, filePath);
    }

    // ================ VendorManager methods ==============================

    @Override
    public Path getVendorManagerFilePath() {
        return vendorStorage.getVendorManagerFilePath();
    }

    @Override
    public Optional<ReadOnlyVendorManager> readVendorManager() throws DataConversionException, IOException {
        return readVendorManager(vendorStorage.getVendorManagerFilePath());
    }

    @Override
    public Optional<ReadOnlyVendorManager> readVendorManager(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return vendorStorage.readVendorManager(filePath);
    }

    @Override
    public void saveVendorManager(ReadOnlyVendorManager vendorManager) throws IOException {
        saveVendorManager(vendorManager, vendorStorage.getVendorManagerFilePath());
    }

    @Override
    public void saveVendorManager(ReadOnlyVendorManager vendorManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        vendorStorage.saveVendorManager(vendorManager, filePath);
    }

}
