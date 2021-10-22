package seedu.address.storage.guest;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.guest.ReadOnlyGuestManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface GuestStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getGuestManagerFilePath();

    /**
     * Returns guest data as a {@link ReadOnlyGuestManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyGuestManager> readGuestManager() throws DataConversionException, IOException;

    /**
     * @see #getGuestManagerFilePath()
     */
    Optional<ReadOnlyGuestManager> readGuestManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyGuestManager} to the storage.
     * @param guestManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveGuestManager(ReadOnlyGuestManager guestManager) throws IOException;

    /**
     * @see #saveGuestManager(ReadOnlyGuestManager)
     */
    void saveGuestManager(ReadOnlyGuestManager guestManager, Path filePath) throws IOException;


}
