package seedu.address.storage.vendor;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.storage.vendor.JsonSerializableVendorManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class JsonVendorStorage implements VendorStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonVendorStorage.class);

    private Path filePath;

    public JsonVendorStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getVendorManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyVendorManager> readVendorManager() throws DataConversionException {
        return readVendorManager(filePath);
    }

    /**
     * Similar to {@link #readVendorManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyVendorManager> readVendorManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableVendorManager> jsonVendorManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableVendorManager.class);
        if (!jsonVendorManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonVendorManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveVendorManager(ReadOnlyVendorManager vendorManager) throws IOException {
        saveVendorManager(vendorManager, filePath);
    }

    /**
     * Similar to {@link #saveVendorManager(ReadOnlyVendorManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveVendorManager(ReadOnlyVendorManager vendorManager, Path filePath) throws IOException {
        requireNonNull(vendorManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableVendorManager(vendorManager), filePath);
    }


}

