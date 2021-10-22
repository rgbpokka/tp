package seedu.address.storage.guest;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.guest.ReadOnlyGuestManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class JsonGuestStorage implements GuestStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonGuestStorage.class);

    private Path filePath;

    public JsonGuestStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getGuestManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyGuestManager> readGuestManager() throws DataConversionException {
        return readGuestManager(filePath);
    }

    /**
     * Similar to {@link #readGuestManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyGuestManager> readGuestManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableGuestManager> jsonGuestManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableGuestManager.class);
        if (!jsonGuestManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonGuestManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveGuestManager(ReadOnlyGuestManager guestManager) throws IOException {
        saveGuestManager(guestManager, filePath);
    }

    /**
     * Similar to {@link #saveGuestManager(ReadOnlyGuestManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveGuestManager(ReadOnlyGuestManager guestManager, Path filePath) throws IOException {
        requireNonNull(guestManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableGuestManager(guestManager), filePath);
    }
    
    
}
