package seedu.address.storage.vendor;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.vendor.ReadOnlyVendorManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface VendorStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getVendorManagerFilePath();

    /**
     * Returns vendor data as a {@link ReadOnlyVendorManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyVendorManager> readVendorManager() throws DataConversionException, IOException;

    /**
     * @see #getVendorManagerFilePath()
     */
    Optional<ReadOnlyVendorManager> readVendorManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyVendorManager} to the storage.
     * @param vendorManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveVendorManager(ReadOnlyVendorManager vendorManager) throws IOException;

    /**
     * @see #saveVendorManager(ReadOnlyVendorManager)
     */
    void saveVendorManager(ReadOnlyVendorManager vendorManager, Path filePath) throws IOException;


}
